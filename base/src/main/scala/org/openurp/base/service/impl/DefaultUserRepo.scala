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

import org.beangle.commons.codec.digest.Digests
import org.beangle.commons.lang.Strings
import org.beangle.commons.logging.Logging
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.beangle.data.jdbc.query.JdbcExecutor
import org.openurp.base.hr.model.{Staff, Teacher}
import org.openurp.base.model.User
import org.openurp.base.service.{UserCategories, UserRepo}
import org.openurp.base.std.model.Student
import org.openurp.code.hr.model.UserCategory

import java.time.{Instant, LocalDate}
import javax.sql.DataSource

class DefaultUserRepo(entityDao: EntityDao, platformDataSource: DataSource, hostname: String) extends UserRepo, Logging {

  var orgId: Int = _

  var domainId: Int = _

  var stdRoleId: Int = _

  var staffRoleId: Int = _

  var teacherRoleId: Int = _

  var tutorRoleId: Int = _

  var emsJdbcExecutor: JdbcExecutor = _

  if (null != platformDataSource) {
    emsJdbcExecutor = new JdbcExecutor(platformDataSource)
    val datas = emsJdbcExecutor.query("select id,org_id from ems.cfg_domains where hostname=?", hostname)
    if (datas.nonEmpty) {
      val first = datas.head
      domainId = first(0).asInstanceOf[Number].intValue
      orgId = first(1).asInstanceOf[Number].intValue
      stdRoleId = emsJdbcExecutor.unique[Int]("select id from ems.usr_roles where domain_id=? and name=?", domainId, "学生").getOrElse(0)
      teacherRoleId = emsJdbcExecutor.unique[Int]("select id from ems.usr_roles where domain_id=? and name=?", domainId, "教师").getOrElse(0)
      staffRoleId = emsJdbcExecutor.unique[Int]("select id from ems.usr_roles where domain_id=? and name=?", domainId, "教职工").getOrElse(0)
      tutorRoleId = emsJdbcExecutor.unique[Int]("select id from ems.usr_roles where domain_id=? and name=?", domainId, "导师").getOrElse(0)
    }
  }

  override def createUser(teacher: Teacher): User = {
    val staff = entityDao.get(classOf[Staff], teacher.staff.id)
    val roleIds = if (teacher.tutorType.isEmpty) List(teacherRoleId) else List(teacherRoleId, tutorRoleId)
    createStaffUser(staff, roleIds, None)
  }

  /**
   * Create a user for staff
   *
   * @param staff
   * @return
   */
  override def createUser(staff: Staff, oldCode: Option[String]): User = {
    createStaffUser(staff, List(staffRoleId), oldCode)
  }

  private def createStaffUser(staff: Staff, roleIds: Seq[Int], oldCode: Option[String]): User = {
    val oldUserCode = oldCode.getOrElse(staff.code)
    val userQuery = OqlBuilder.from(classOf[User], "user").where("user.code=:code", oldUserCode)
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
    createAccount(findEmsUserId(oldUserCode), user, password, UserCategories.Teacher, roleIds)
    user
  }

  override def createUser(std: Student, oldCode: Option[String]): User = {
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
        newUser.email = Option(newUser.code + "@unknown.com")
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
    createAccount(findEmsUserId(oldCode.getOrElse(std.code)), user, password, UserCategories.Student, List(stdRoleId))
    user
  }

  override def activate(users: Iterable[User], active: Boolean): Unit = {
    users.foreach { user =>
      if active then user.endOn = null
      else user.endOn = Option(LocalDate.now())
    }
    entityDao.saveOrUpdate(users)
    users.foreach(user => {
      emsJdbcExecutor.update("update ems.usr_users u set end_on=?  where u.org_id= ? and u.code=?",
        user.endOn, if (null == user.endOn) true else false, orgId, user.code)
      emsJdbcExecutor.update("update ems.usr_accounts a set end_on=? where a.domain_id= ? and " +
        "exists(select * from ems.usr_users u where u.id=a.user_id and  u.code=?)",
        user.endOn, if (null == user.endOn) true else false, domainId, user.code)
    })
  }

  override def updatePassword(userCode: String, password: String): Int = {
    val encoded = "{MD5}" + Digests.md5Hex(password)
    emsJdbcExecutor
      .update("update ems.usr_accounts acc set password=? where acc.domain_id=? and " +
        "exists(select * from ems.usr_users u where u.code=? and u.id=acc.user_id)", encoded, domainId, userCode)
  }

  override def createAccount(user: User): Unit = {
    val password = defaultPassword(user.code)
    val roleId = user.category.id match {
      case UserCategories.Student => stdRoleId
      case UserCategories.Teacher => teacherRoleId
      case _ => 0
    }
    createAccount(findEmsUserId(user.code), user, password, user.category.id, List(roleId))
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

  private def findEmsUserId(code: String): Option[java.lang.Long] = {
    emsJdbcExecutor.unique[java.lang.Long]("select id from ems.usr_users where org_id=" + orgId + " and code=? ", code)
  }

  private def createAccount(existId: Option[java.lang.Long], user: User, password: String, categoryId: Int, roleIds: Seq[Int]): Unit = {
    val code = user.code
    val name = user.name

    val userId = existId match {
      case Some(id) =>
        val data = emsJdbcExecutor.query("select code,name,mobile,email from ems.usr_users where id=?", id).head
        if (data(0) != code || data(1) != name) {
          emsJdbcExecutor.update("update ems.usr_users set code=?, name=?, updated_at=now() where id=?", code, name, id)
          logger.info(s"change ${id} code and name to ${code} ${name}")
        }
        user.mobile foreach { mobile =>
          if (mobile != data(2)) emsJdbcExecutor.update("update ems.usr_users set mobile=?,updated_at=now() where id=?", mobile, id)
        }
        user.email foreach { email =>
          if (email != data(3)) emsJdbcExecutor.update("update ems.usr_users set email=?,updated_at=now() where id=?", email, id)
        }
        id
      case None =>
        val userId = emsJdbcExecutor.unique[java.lang.Long]("select datetime_id()").getOrElse(0L)
        emsJdbcExecutor.update("insert into ems.usr_users(id,code,name,org_id,category_id,mobile,email,updated_at,begin_on)"
          + "values(?,?,?,?,?,?,?,now(),current_date);", userId, code, name, orgId, categoryId, user.mobile.orNull, user.email.orNull)
        logger.info(s"create user ${code} ${name}")
        userId
    }
    val accountCount = emsJdbcExecutor.unique[Long]("select count(*) from ems.usr_accounts where user_id=? and domain_id=? ", userId, domainId).getOrElse(0L)
    if (accountCount == 0) {
      val accountId = emsJdbcExecutor.unique[java.lang.Long]("select datetime_id()")
      emsJdbcExecutor.update(
        "insert into ems.usr_accounts(id,user_id,domain_id,password,passwd_expired_on,locked,enabled,begin_on,updated_at)"
          + "values(?,?,?,?,current_date+180,false,true,current_date,now());",
        accountId.get, userId, domainId, "{MD5}" + Digests.md5Hex(password))
    }
    roleIds foreach { roleId =>
      if (roleId > 0) {
        val roleCount = emsJdbcExecutor.unique[Long]("select count(*) from ems.usr_role_members where user_id=? and role_id=?", userId, roleId).getOrElse(0L)
        if (roleCount == 0) {
          emsJdbcExecutor.update("insert into ems.usr_role_members(id,user_id,role_id,is_member,is_granter,is_manager,updated_at)"
            + "values(datetime_id(),?,?,true,false,false,now());", userId, roleId)
        }
      }
    }
  }
}
