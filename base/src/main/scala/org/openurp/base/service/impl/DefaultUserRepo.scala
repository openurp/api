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
import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.Strings
import org.beangle.commons.logging.Logging
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.beangle.jdbc.query.JdbcExecutor
import org.openurp.base.hr.model.*
import org.openurp.base.model.{Department, Project, User, UserGroup}
import org.openurp.base.service.{UserCategories, UserRepo}
import org.openurp.base.std.model.Student
import org.openurp.code.hr.model.UserCategory

import java.time.{Instant, LocalDate}
import javax.sql.DataSource

/** URP 用户同步服务
 *
 * @param entityDao
 * @param platformDataSource
 * @param hostname
 */
class DefaultUserRepo(entityDao: EntityDao, platformDataSource: DataSource, hostname: String) extends UserRepo, Logging {

  private var orgId: Int = _
  private var domainId: Int = _
  private var dimensionProjectId: Int = _
  private var dimensionDepartmentId: Int = _

  private var emsJdbcExecutor: JdbcExecutor = _

  if (null != platformDataSource) {
    emsJdbcExecutor = new JdbcExecutor(platformDataSource)
    val datas = emsJdbcExecutor.query("select id,org_id from ems.cfg_domains where hostname=?", hostname)
    if (datas.nonEmpty) {
      val first = datas.head
      domainId = first(0).asInstanceOf[Number].intValue
      orgId = first(1).asInstanceOf[Number].intValue
      dimensionProjectId = emsJdbcExecutor.unique[Int]("select id from ems.usr_dimensions where domain_id=? and name=?", domainId, "project").getOrElse(0)
      dimensionDepartmentId = emsJdbcExecutor.unique[Int]("select id from ems.usr_dimensions where domain_id=? and name=?", domainId, "department").getOrElse(0)
    }
  }

  /** Create a user for staff
   *
   * @param staff staff
   * @return
   */
  override def createUser(staff: Staff, oldCode: Option[String]): User = {
    val secretaries = entityDao.findBy(classOf[Secretary], "staff", staff)
    val mentors = entityDao.findBy(classOf[Mentor], "staff", staff)
    var groupCodes: collection.mutable.Buffer[String] = Collections.newBuffer[String]
    if (secretaries.nonEmpty) {
      secretaries foreach { s =>
        groupCodes = s.projects.map(p => s"secretary.${p.id}").toBuffer.sorted
        groupCodes.insert(0, "secretary")
      }
    } else if (mentors.nonEmpty) {
      mentors foreach { m =>
        groupCodes = m.projects.map(p => s"mentor.${p.id}").toBuffer.sorted
        groupCodes.insert(0, "mentor")
      }
    } else {
      val tutors = entityDao.findBy(classOf[Tutor], "staff", staff)
      if (tutors.nonEmpty) groupCodes.addOne("tutor")
      val teachers = entityDao.findBy(classOf[Teacher], "staff", staff)
      teachers foreach { t =>
        groupCodes = t.projects.map(p => s"teacher.${p.id}").toBuffer.sorted
        groupCodes.insert(0, "teacher")
      }
      groupCodes.addOne("staff")
    }
    val user = createStaffUser(staff, getGroups(groupCodes), oldCode)
    // 创建秘书的数据权限
    secretaries foreach { s =>
      s.projects foreach { p => createProfile(user.id, p, staff.department) }
    }
    user
  }

  private def createStaffUser(staff: Staff, groups: Seq[UserGroup], oldCode: Option[String]): User = {
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

    user.addGroups(groups)
    entityDao.saveOrUpdate(user)

    val password = defaultPassword(staff.idNumber.orNull)
    createAccount(findEmsUserId(oldUserCode), user, password, UserCategories.Teacher)
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
        newUser.group = getGroups(Seq(s"student.${std.project.id}", "student")).headOption
        newUser
    }
    user.department = std.state.get.department
    user.name = std.name
    user.gender = std.gender
    user.updatedAt = Instant.now()
    entityDao.saveOrUpdate(user)

    createAccount(findEmsUserId(oldCode.getOrElse(std.code)), user, defaultPassword(std.person.code), UserCategories.Student)
    user
  }

  override def activate(users: Iterable[User], active: Boolean): Unit = {
    users.foreach { user =>
      if active then user.endOn = null
      else user.endOn = Option(LocalDate.now())
    }
    entityDao.saveOrUpdate(users)
    users.foreach(user => {
      emsJdbcExecutor.update("update ems.usr_users u set end_on=? ,enabled=true,locked=false where u.org_id= ? and u.code=?",
        user.endOn, if (null == user.endOn) true else false, orgId, user.code)
    })
  }

  override def updatePassword(user: User, password: String): Int = {
    val encoded = "{MD5}" + Digests.md5Hex(password)
    emsJdbcExecutor.update("update ems.usr_users u set password=? where u.org_id=? and u.code=?", encoded, user.school.id, user.code)
  }

  override def createAccount(user: User): Unit = {
    createAccount(findEmsUserId(user), user, defaultPassword(user.code), user.category.id)
  }

  private def defaultPassword(idNumber: String): String = {
    if Strings.isEmpty(idNumber) then "123456"
    else {
      if idNumber.length > 6 then Strings.substring(idNumber, idNumber.length - 6, idNumber.length)
      else idNumber
    }
  }

  private def findEmsUserId(code: String): Option[Long] = {
    emsJdbcExecutor.unique[Long]("select id from ems.usr_users where org_id=" + orgId + " and code=? ", code)
  }

  private def findEmsUserId(user: User): Option[Long] = {
    emsJdbcExecutor.unique[Long]("select id from ems.usr_users where org_id=" + user.school.id + " and code=? ", user.code)
  }

  private def findEmsGroupId(group: UserGroup): Option[Int] = {
    emsJdbcExecutor.unique[Int]("select id from ems.usr_groups where org_id=" + group.school.id + " and code=? ", group.code)
  }

  private def createAccount(existId: Option[Long], user: User, password: String, categoryId: Int): Unit = {
    val code = user.code
    val name = user.name
    val groupId = user.group.flatMap(findEmsGroupId)

    val userId = existId match {
      case Some(id) =>
        val data = emsJdbcExecutor.query("select code,name,mobile,email,group_id from ems.usr_users where id=?", id).head
        if (data(0) != code || data(1) != name) {
          emsJdbcExecutor.update("update ems.usr_users set code=?, name=?, updated_at=now() where id=?", code, name, id)
          logger.info(s"change user($id) code and name to $code $name")
        }
        user.mobile foreach { mobile =>
          if (mobile != data(2)) emsJdbcExecutor.update("update ems.usr_users set mobile=?,updated_at=now() where id=?", mobile, id)
        }
        user.email foreach { email =>
          if (email != data(3)) emsJdbcExecutor.update("update ems.usr_users set email=?,updated_at=now() where id=?", email, id)
        }
        groupId foreach { gid =>
          if (gid != data(4)) emsJdbcExecutor.update("update ems.usr_users set group_id=?,updated_at=now() where id=?", gid, id)
        }
        id
      case None =>
        val userId = nextId()
        emsJdbcExecutor.update("insert into ems.usr_users(id,code,name,org_id,category_id,mobile,email,password,group_id," +
          "passwd_expired_on,updated_at,begin_on,enabled,locked)"
          + "values(?,?,?,?,?,?,?,?,current_date+180,now(),current_date,true,false);", userId, code, name, orgId,
          categoryId, user.mobile.orNull, user.email.orNull, "{MD5}" + Digests.md5Hex(password), groupId)
        logger.info(s"create user $code $name")
        userId
    }
    grantGroups(userId, user.groups.map(_.group.id))
  }

  private def createProfile(userId: Long, project: Project, department: Department): Unit = {
    val departId = department.id.toString
    var missingProject = true
    val existProfileIds = emsJdbcExecutor.query(s"select id from ems.usr_profiles where domain_id=$domainId and user_id=$userId")
    if (existProfileIds.nonEmpty) {
      for (pid <- existProfileIds if missingProject) {
        val profileId = pid.head.asInstanceOf[Number].longValue()
        val projectValues = emsJdbcExecutor.query("select value_ from ems.usr_profiles_properties where profile_id=? and dimension_id=?", profileId, dimensionProjectId)
        if (projectValues.nonEmpty) {
          val projectValue = projectValues.head.head
          if (projectValue == "*" || projectValue == project.id.toString) {
            missingProject = false
            var missingDepart = true
            var departValue: String = null
            val departValues = emsJdbcExecutor.query("select value_ from ems.usr_profiles_properties where profile_id=? and dimension_id=?", profileId, dimensionDepartmentId)
            if (departValues.nonEmpty) {
              departValue = departValues.head.head.toString
              if (departValue == "*" || Strings.split(departValue).toSet.contains(departId)) {
                missingDepart = false
              }
            }
            if (missingDepart) {
              if (null == departValue) {
                emsJdbcExecutor.update("insert into ems.usr_profiles_properties(profile_id,dimension_id,value_) values(?,?,?);", profileId, dimensionDepartmentId, departId)
              } else {
                departValue += ("," + department.id.toString)
                emsJdbcExecutor.update("update ems.usr_profiles_properties set value_=? where profile_id=? and dimension_id=?", departValue, profileId, dimensionDepartmentId)
              }
            }
          }
        }
      }
    }
    if (missingProject) {
      val profileId = nextId()
      emsJdbcExecutor.update("insert into ems.usr_profiles(id,user_id,domain_id,name) values(?,?,?,?);", profileId, userId, domainId, project.name)
      emsJdbcExecutor.update("insert into ems.usr_profiles_properties(profile_id,dimension_id,value_) values(?,?,?);", profileId, dimensionProjectId, project.id.toString)
      emsJdbcExecutor.update("insert into ems.usr_profiles_properties(profile_id,dimension_id,value_) values(?,?,?);", profileId, dimensionDepartmentId, departId)
    }
  }

  private def grantGroups(userId: Long, groupIds: Iterable[Int]): Unit = {
    groupIds foreach { groupId =>
      if (groupId > 0) {
        val cnt = emsJdbcExecutor.unique[Long]("select count(*) from ems.usr_group_members where user_id=? and group_id=?", userId, groupId).getOrElse(0L)
        if (cnt == 0) {
          emsJdbcExecutor.update("insert into ems.usr_role_members(id,user_id,group_id,updated_at)"
            + "values(datetime_id(),?,?,now());", userId, groupId)
        }
      }
    }
  }

  private def getGroups(codes: Iterable[String]): Seq[UserGroup] = {
    if (codes.isEmpty) return Seq.empty
    val q = OqlBuilder.from(classOf[UserGroup], "g")
    q.where("g.codes in (:codes)", codes)
    q.cacheable()
    entityDao.search(q)
  }

  private def nextId(): Long = {
    emsJdbcExecutor.unique[Long]("select datetime_id()").get
  }
}
