/*
 * Copyright (C) 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openurp.base.service.impl

import org.beangle.commons.bean.Initializing
import org.beangle.commons.codec.digest.Digests
import org.beangle.commons.lang.Strings
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.beangle.data.jdbc.query.JdbcExecutor
import org.openurp.base.model.{Staff, User}
import org.openurp.base.service.{UserCategories, UserRepo}
import org.openurp.base.std.model.Student
import org.openurp.code.hr.model.UserCategory

import java.time.{Instant, LocalDate}
import javax.sql.DataSource

class DefaultUserRepo(entityDao: EntityDao, platformDataSource: DataSource, hostname: String) extends UserRepo {

  var orgId: Int = _

  var domainId: Int = _

  var stdRoleId: Int = _

  var teacherRoleId: Int = _

  var platformJdbcExecutor: JdbcExecutor = _

  if (null != platformDataSource) {
    platformJdbcExecutor = new JdbcExecutor(platformDataSource)
    val datas = platformJdbcExecutor.query("select id,org_id from cfg.domains where hostname=?", hostname)
    if (datas.nonEmpty) {
      val first = datas.head
      domainId = first(0).asInstanceOf[Number].intValue
      orgId = first(1).asInstanceOf[Number].intValue
      stdRoleId = platformJdbcExecutor.unique[Int]("select id from usr.roles where domain_id=? and name=?", domainId, "学生").getOrElse(0)
      teacherRoleId = platformJdbcExecutor.unique[Int]("select id from usr.roles where domain_id=? and name=?", domainId, "教师").getOrElse(0)
    }
  }

  override def createUser(staff: Staff): User = {
    var userCode: String = staff.code
    if (staff.persisted) {
      val existQuery = OqlBuilder.from[String](classOf[Staff].getName, "t").select("t.code")
      existQuery.where("t.id = :staffId", staff.id)
      entityDao.search(existQuery).headOption foreach { code =>
        userCode = code
      }
    }

    val userQuery = OqlBuilder.from(classOf[User], "user").where("user.code=:code", userCode)
      .where("user.school =:school", staff.school)
    val users = entityDao.search(userQuery)
    val user =
      if (users.size == 1) {
        users.head
      } else {
        val u = new User
        u.school = staff.school
        u.category = UserCategory(UserCategories.Teacher)
        u
      }
    user.beginOn = staff.beginOn
    user.endOn = staff.endOn
    user.updatedAt = Instant.now
    user.gender = staff.gender
    user.department = staff.department
    user.code = staff.code
    user.name = staff.name
    user.mobile = staff.mobile
    user.email = staff.email
    user.updatedAt = Instant.now

    entityDao.saveOrUpdate(user)

    val password = defaultPassword(staff.idNumber.orNull)
    createAccount(orgId, domainId, staff.code, staff.name, password, UserCategories.Teacher, teacherRoleId)
    user
  }

  override def createUser(std: Student): User = {
    val school = std.project.school
    val userBuilder = OqlBuilder.from(classOf[User], "user")
    userBuilder.where("user.code=:code", std.code)
    userBuilder.where("user.school=:school", school)
    val users = entityDao.search(userBuilder)
    val user = users.headOption match {
      case Some(value) => value
      case None =>
        val newUser = new User
        newUser.school = school
        newUser.code = std.code
        val category = entityDao.get(classOf[UserCategory], UserCategories.Student)
        newUser.category = category
        newUser.email = Option(newUser.code + "@unkown.com")
        newUser.beginOn = std.beginOn
        newUser.endOn = Option(std.endOn)
        newUser
    }
    user.department = std.state.get.department
    user.name = std.name
    user.gender = std.gender
    user.updatedAt = Instant.now()
    entityDao.saveOrUpdate(user)

    val password = defaultPassword(std.person.code)
    createAccount(orgId, domainId, std.code, std.name, password, UserCategories.Student, stdRoleId)
    user
  }

  override def activate(users: Iterable[User], active: Boolean): Unit = {
    users.foreach { user =>
      if active then user.endOn = null
      else user.endOn = Option(LocalDate.now())
    }
    entityDao.saveOrUpdate(users)
    users.foreach(user => {
      platformJdbcExecutor.update("update usr.users u set end_on=?  where u.org_id= ? and u.code=?", user.endOn, if (null == user.endOn) true else false, orgId, user.code)
      platformJdbcExecutor.update("update usr.accounts a set end_on=? where a.domain_id= ? and exists(select * from  usr.users u where u.id=a.user_id and  u.code=?)", user.endOn, if (null == user.endOn) true else false, domainId, user.code)
    })
  }

  override def updatePassword(userCode: String, password: String): Int = {
    val encoded = "{MD5}" + Digests.md5Hex(password)
    platformJdbcExecutor
      .update("update usr.accounts acc set password=? where acc.domain_id=? and exists(select * from usr.users u where u.code=? and u.id=acc.user_id)", encoded, domainId, userCode)
  }

  private def defaultPassword(idNumber: String): String = {
    if Strings.isEmpty(idNumber) then "123456"
    else {
      if idNumber.length > 6 then
        Strings.substring(idNumber, idNumber.length - 6, idNumber.length)
      else
        idNumber
    }
  }

  private def createAccount(orgId: Int, domainId: Int, code: String, name: String, password: String, categoryId: Int, roleId: Long): Unit = {
    val userIds = platformJdbcExecutor.unique[java.lang.Long]("select id from usr.users where org_id=" + orgId + " and code=? ", code)
    var userId = userIds match {
      case Some(id) => id
      case None =>
        val userId = platformJdbcExecutor.unique[java.lang.Long]("select datetime_id()").getOrElse(0L)
        platformJdbcExecutor.update("insert into usr.users (id,code,name,org_id,category_id,updated_at,begin_on)"
          + "values(?,?,?,?,?,now(),current_date);", userId, code, name, orgId, categoryId);
    }
    val accountCount = platformJdbcExecutor.unique[Long]("select count(*) from usr.accounts where user_id=? and domain_id=? ", userId, domainId).getOrElse(0L)
    if (accountCount == 0) {
      val accountId = platformJdbcExecutor.unique[java.lang.Long]("select datetime_id()")
      platformJdbcExecutor.update(
        "insert into usr.accounts (id,user_id,domain_id,password,passwd_expired_on,locked,enabled,begin_on,updated_at)"
          + "values(?,?,?,?,current_date+180,false,true,current_date,now());",
        accountId.get, userId, domainId, "{MD5}" + Digests.md5Hex(password))
    }
    val roleCount = platformJdbcExecutor.unique[Long]("select count(*) from usr.role_members where user_id=? and role_id=? ", userId, roleId).getOrElse(0L)
    if (roleCount == 0) {
      platformJdbcExecutor.update("insert into usr.role_members(id,user_id,role_id,is_member,is_granter,is_manager,updated_at)"
        + "values(datetime_id(),?,?,true,false,false,current_date);", userId, roleId)
    }
  }
}
