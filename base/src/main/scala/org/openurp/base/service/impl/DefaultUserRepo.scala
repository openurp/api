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
import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.Strings
import org.beangle.commons.logging.Logging
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.beangle.jdbc.query.JdbcExecutor
import org.openurp.base.hr.model.*
import org.openurp.base.model.*
import org.openurp.base.service.{UserCategories, UserRepo}
import org.openurp.base.std.model.Student
import org.openurp.code.hr.model.UserCategory

import java.time.{Instant, LocalDate}
import javax.sql.DataSource
import scala.util.Random

/** URP 用户同步服务
 *
 * @param entityDao
 * @param platformDataSource
 * @param hostname
 */
class DefaultUserRepo(entityDao: EntityDao, platformDataSource: DataSource, hostname: String) extends UserRepo, Logging, Initializing {

  private var orgId: Int = _
  private var domainId: Int = _
  private var dimensionProjectId: Int = _
  private var dimensionDepartmentId: Int = _

  private var emsJdbcExecutor: JdbcExecutor = _

  /** 用户过期后，账户仍然可用的天数 */
  var idleDays: Int = 90

  override def init(): Unit = {
    require(idleDays >= 0, "账户停滞天数需要为非负整数")
  }

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

  override def createDepart(depart: Department): Unit = {
    val emsDepart = emsJdbcExecutor.query("select id,name from ems.usr_departs where org_id=" + depart.school.id + " and code=? ", depart.code).headOption
    emsDepart match {
      case None =>
        emsJdbcExecutor.update("insert into ems.usr_departs(id,code,name,short_name,indexno,begin_on,updated_at,org_id) values(next_id('ems.usr_departs'),?,?,?,?,?,?,?)",
          depart.code, depart.name, depart.shortName.orNull, depart.indexno, depart.beginOn, depart.updatedAt, depart.school.id)
        logger.info(s"create depart ${depart.name}")
      case Some(d) =>
        if (d(1) != depart.name) {
          emsJdbcExecutor.update("update ems.usr_departs set name=? where id=?", depart.name, d(0))
        }
    }
  }

  /** Create a user for staff
   *
   * @param staff staff
   * @return
   */
  override def createUser(staff: Staff, oldCode: Option[String]): User = {
    val secretaries = entityDao.findBy(classOf[Secretary], "staff", staff)
    val school = staff.school
    val mentors = entityDao.findBy(classOf[Mentor], "staff", staff)
    val groups: collection.mutable.Buffer[UserGroup] = Collections.newBuffer[UserGroup]
    if (secretaries.nonEmpty) {
      secretaries foreach { s =>
        groups.addAll(getGroups(school, s.projects.map(p => s"secretary.${p.id}")))
        groups.addAll(getGroups(school, List("secretary")))
      }
    } else if (mentors.nonEmpty) {
      mentors foreach { m =>
        groups.addAll(getGroups(school, m.projects.map(p => s"mentor.${p.id}")))
        groups.addAll(getGroups(school, List("mentor")))
      }
    } else {
      if (staff.tutorType.nonEmpty) groups.addAll(getGroups(school, List("tutor")))
      val teachers = entityDao.findBy(classOf[Teacher], "staff", staff)
      teachers foreach { t =>
        groups.addAll(getGroups(school, t.projects.map(p => s"teacher.${p.id}")))
        groups.addAll(getGroups(school, List("teacher")))
      }
      groups.addAll(getGroups(school, List("staff")))
    }
    val user = createStaffUser(staff, groups, oldCode)
    // 创建秘书的数据权限
    val emsUserId = findEmsUserId(user).get
    secretaries foreach { s =>
      s.projects foreach { p => createProfile(emsUserId, p, staff.department) }
    }
    user
  }

  private def createStaffUser(staff: Staff, groups: collection.Seq[UserGroup], oldCode: Option[String]): User = {
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
    //有条件的更新教职工账户的结束日期
    user.endOn match
      case None =>
        if (!user.persisted) user.endOn = staff.endOn.map(_.plusDays(idleDays)) //如果是新用户则设置过期日期+idleDays天
      case Some(endOn) =>
        staff.endOn foreach { staffEndOn =>
          if (staffEndOn.isAfter(endOn)) user.endOn = Some(staffEndOn.plusDays(idleDays))
        }
    user.updatedAt = Instant.now
    user.gender = staff.gender
    user.department = staff.department
    user.code = staff.code
    user.name = staff.name
    user.mobile = staff.mobile
    user.email = staff.email
    user.updatedAt = Instant.now

    user.updateGroups(groups)
    entityDao.saveOrUpdate(user)
    entityDao.refresh(user) //这句非常重要，否则user.department中只有ID，但是创建账户需要depart.school属性

    val password = defaultPassword(staff.idNumber.orNull)
    createAccount(findEmsUserId(oldUserCode), user, password, UserCategories.Teacher)
    user
  }

  override def createUser(std: Student, userCode: String, oldCode: Option[String]): User = {
    val school = std.project.school

    val userBuilder = OqlBuilder.from(classOf[User], "user")
    userBuilder.where("user.code=:code", userCode)
    userBuilder.where("user.school=:school", school)
    val users = entityDao.search(userBuilder)
    val user = users.headOption match {
      case Some(value) => value
      case None =>
        val newUser = new User
        newUser.school = school
        newUser.code = userCode
        val category = entityDao.get(classOf[UserCategory], UserCategories.Student)
        newUser.category = category
        newUser.email = Option(newUser.code + "@unknown.com")
        newUser.beginOn = std.beginOn
        newUser.endOn = Option(std.maxEndOn.plusDays(idleDays)) //初始为结束日期+idleDays天
        newUser
    }
    if (user.group.isEmpty) {
      user.group = getGroups(school, Seq(s"student.${std.project.id}", "student")).headOption
    }
    user.department = std.state.get.department
    user.name = std.name
    user.gender = std.gender
    if !user.persisted then user.updatedAt = Instant.now()
    entityDao.saveOrUpdate(user)

    createAccount(findEmsUserId(oldCode.getOrElse(userCode)), user, defaultPassword(std.person.code), UserCategories.Student)
    std.user = user
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

  /** 默认证件后六位，如果没有证件则是一个随机的默认密码
   *
   * @param idNumber
   * @return
   */
  private def defaultPassword(idNumber: String): String = {
    if Strings.isEmpty(idNumber) then generatePassword()
    else {
      if idNumber.length > 6 then Strings.substring(idNumber, idNumber.length - 6, idNumber.length)
      else idNumber
    }
  }

  private def generatePassword(): String = {
    (0 until 10).map(i => Random.nextPrintableChar).mkString
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

  private def findEmsDepartId(d: Department): Option[Int] = {
    emsJdbcExecutor.unique[Int]("select id from ems.usr_departs where org_id=" + d.school.id + " and code=? ", d.code)
  }

  private def createAccount(existId: Option[Long], user: User, defaultPassword: String, categoryId: Int): Unit = {
    val code = user.code
    val name = user.name
    val groupId = user.group.flatMap(findEmsGroupId)
    val departId = findEmsDepartId(user.department)

    val userId = existId match {
      case Some(id) =>
        val data = emsJdbcExecutor.query("select code,name,mobile,email,group_id,depart_id,begin_on,end_on from ems.usr_users where id=?", id).head
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
        departId foreach { did =>
          if (did != data(5)) emsJdbcExecutor.update("update ems.usr_users set depart_id=?,updated_at=now() where id=?", did, id)
        }
        if (user.beginOn != data(6)) {
          emsJdbcExecutor.update("update ems.usr_users set begin_on=?,updated_at=now() where id=?", user.beginOn, id)
        }
        if (user.endOn.isEmpty && null != data(7)) {
          emsJdbcExecutor.update("update ems.usr_users set end_on=null,updated_at=now() where id=?", id)
        } else if (user.endOn.nonEmpty && (null == data(7) || !user.endOn.contains(data(7)))) {
          emsJdbcExecutor.update("update ems.usr_users set end_on=?,updated_at=now() where id=?", user.endOn.get, id)
        }
        id
      case None =>
        val userId = nextId()
        emsJdbcExecutor.update("insert into ems.usr_users(id,code,name,org_id,category_id,mobile,email,password,group_id,depart_id," +
          "begin_on,end_on,passwd_expired_on,updated_at,enabled,locked)"
          + "values(?,?,?,?,?,?,?,?,?,?,?,?,current_date+180,now(),true,false);", userId, code, name, orgId,
          categoryId, user.mobile.orNull, user.email.orNull, "{MD5}" + Digests.md5Hex(defaultPassword), groupId.orNull, departId.orNull,
          user.beginOn, user.endOn.orNull
        )
        logger.info(s"create user $code $name")
        userId
    }

    grantGroups(userId, user.groups.flatMap(x => findEmsGroupId(x.group)))
  }

  private def createProfile(emsUserId: Long, project: Project, department: Department): Unit = {
    val departId = department.id.toString
    var missingProject = true
    val existProfileIds = emsJdbcExecutor.query(s"select id from ems.usr_profiles where domain_id=$domainId and user_id=$emsUserId")
    if (existProfileIds.nonEmpty) {
      for (pid <- existProfileIds if missingProject) {
        val profileId = pid.head.asInstanceOf[Number].longValue()
        val projectValues = emsJdbcExecutor.query("select value_ from ems.usr_profiles_properties where profile_id=? and dimension_id=?", profileId, dimensionProjectId)
        if (projectValues.nonEmpty) {
          val projectValue = projectValues.head.head.toString.trim()
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
      emsJdbcExecutor.update("insert into ems.usr_profiles(id,user_id,domain_id,name) values(?,?,?,?);", profileId, emsUserId, domainId, project.name)
      emsJdbcExecutor.update("insert into ems.usr_profiles_properties(profile_id,dimension_id,value_) values(?,?,?);", profileId, dimensionProjectId, project.id.toString)
      emsJdbcExecutor.update("insert into ems.usr_profiles_properties(profile_id,dimension_id,value_) values(?,?,?);", profileId, dimensionDepartmentId, departId)
    }
  }

  private def grantGroups(userId: Long, groupIds: Iterable[Int]): Unit = {
    groupIds foreach { groupId =>
      if (groupId > 0) {
        val cnt = emsJdbcExecutor.unique[Long]("select count(*) from ems.usr_group_members where user_id=? and group_id=?", userId, groupId).getOrElse(0L)
        if (cnt == 0) {
          emsJdbcExecutor.update("insert into ems.usr_group_members(id,user_id,group_id,updated_at)"
            + "values(datetime_id(),?,?,now());", userId, groupId)
        }
      }
    }
  }

  private def getGroups(school: School, codes: Iterable[String]): Seq[UserGroup] = {
    if (codes.isEmpty) return Seq.empty
    val q = OqlBuilder.from(classOf[UserGroup], "g")
    q.where("g.school=:school and g.code in (:codes)", school, codes)
    q.cacheable()
    entityDao.search(q)
  }

  private def nextId(): Long = {
    emsJdbcExecutor.unique[Long]("select datetime_id()").get
  }
}
