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

package org.openurp.starter.web.support

import org.beangle.commons.lang.Strings
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.beangle.data.model.Entity
import org.beangle.security.Securities
import org.beangle.security.authc.{DefaultAccount, Profile}
import org.beangle.webmvc.annotation.ignore
import org.beangle.webmvc.support.{ParamSupport, ServletSupport}
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.{Department, Project, Semester, User}
import org.openurp.base.service.{Feature, ProjectConfigService, SemesterService}
import org.openurp.base.std.model.Student
import org.openurp.code.Code
import org.openurp.code.service.CodeService
import org.openurp.starter.web.helper.EmsCookieHelper

import java.time.LocalDate

trait ProjectSupport extends ParamSupport with ServletSupport {

  def entityDao: EntityDao

  var codeService: CodeService = _

  var configService: ProjectConfigService = _

  var semesterService: SemesterService = _

  protected def getConfig[T](name: String, defaultValue: T)(using project: Project): T = {
    configService.get(project, name, defaultValue)
  }

  protected def getConfig(f: Feature)(using project: Project): Any = {
    configService.get[Any](project, f)
  }

  def getCodes[T <: Code](clazz: Class[T])(using project: Project): collection.Seq[T] = {
    codeService.get(clazz)
  }

  protected def findInSchool[T <: Entity[_]](clazz: Class[T])(using project: Project): Seq[T] = {
    val query = OqlBuilder.from(clazz, "aa")
    query.where("aa.school=:school", project.school)
    query.orderBy("code")
    entityDao.search(query)
  }

  @ignore
  protected final def getProject: Project = {
    Securities.session match
      case Some(s) =>
        val account = s.principal.asInstanceOf[DefaultAccount]
        if (null != account.profiles && account.profiles.length > 0) {
          new EmsCookieHelper(entityDao).getProject(request, response)
        } else {
          //没有数据权限则使用默认项目
          val stds = entityDao.findBy(classOf[Student], "code" -> Securities.user)
          if (stds.nonEmpty) {
            stds.head.project
          } else {
            val teachers = entityDao.findBy(classOf[Teacher], "staff.code" -> Securities.user)
            if (teachers.nonEmpty) {
              if (teachers.head.projects.nonEmpty) {
                teachers.head.projects.head
              } else {
                getFirstProject()
              }
            } else {
              getFirstProject()
            }
          }
        }
      case None =>
        getFirstProject()

  }

  protected def findInProject[T <: Entity[_]](clazz: Class[T], orderBy: String = "code")(using project: Project): Seq[T] = {
    val query = OqlBuilder.from(clazz, "aa")
    query.where("aa.project=:project", project)
    query.orderBy(orderBy)
    entityDao.search(query)
  }

  protected def queryByDepart[T](query: OqlBuilder[T], departPath: String): OqlBuilder[T] = {
    getProfileDepartIds match {
      case None => query.where("1=2")
      case Some(d) =>
        if (d != Profile.AllValue) {
          val departIds = Strings.splitToInt(d)
          if (departPath.endsWith(".id")) {
            query.where(departPath + " in(:profile_depart_ids)", departIds)
          } else {
            query.where(departPath + ".id in(:profile_depart_ids)", departIds)
          }
        }
    }
    query
  }

  protected def queryByDimension[T](query: OqlBuilder[T], departPath: String, stdTypePath: String): OqlBuilder[T] = {
    if (Strings.isNotBlank(departPath)) {
      getProfileDepartIds match {
        case None => query.where("1=2")
        case Some(d) =>
          if (d != Profile.AllValue) {
            val departIds = Strings.splitToInt(d)
            if (departPath.endsWith(".id")) {
              query.where(departPath + " in(:profile_depart_ids)", departIds)
            } else {
              query.where(departPath + ".id in(:profile_depart_ids)", departIds)
            }
          }
      }
    }
    if (Strings.isNotBlank(stdTypePath)) {
      getProfileStdTypeIds foreach { d =>
        if (d != Profile.AllValue) {
          val stdTypeIds = Strings.splitToInt(d)
          if (stdTypePath.endsWith(".id")) {
            query.where(stdTypePath + " in(:profile_stdtype_ids)", stdTypeIds)
          } else {
            query.where(stdTypePath + ".id in(:profile_stdtype_ids)", stdTypeIds)
          }
        }
      }
    }
    query
  }

  protected def getDeparts(using project: Project): Seq[Department] = {
    getProfileDepartIds match {
      case None => List.empty
      case Some(d) =>
        val q = OqlBuilder.from(classOf[Department], "d")
        q.where("d.school=:school", project.school)
        q.cacheable()
        val departs = entityDao.search(q)
        val ids = if d == Profile.AllValue then Set.empty[Int] else Strings.splitToInt(d).toSet
        val now = LocalDate.now()
        val pds = project.departments.toSet
        val filted = departs.filter { d =>
          pds.contains(d) && (ids.isEmpty || ids.contains(d.id)) && (d.endOn.isEmpty || !d.endOn.get.isAfter(now))
        }
        filted.sortBy(_.indexno)
    }
  }

  protected def getSchoolDeparts(using project: Project): Seq[Department] = {
    getProfileDepartIds match {
      case None => List.empty
      case Some(d) =>
        val q = OqlBuilder.from(classOf[Department], "d")
        q.where("d.school=:school", project.school)
        q.cacheable()
        val departs = entityDao.search(q)
        val ids = if d == Profile.AllValue then Set.empty[Int] else Strings.splitToInt(d).toSet
        val now = LocalDate.now()
        val filted = departs.filter { d => (ids.isEmpty || ids.contains(d.id)) && (d.endOn.isEmpty || !d.endOn.get.isAfter(now)) }
        filted.sortBy(_.indexno)
    }
  }

  private def getProfileDepartIds: Option[String] = {
    new EmsCookieHelper(entityDao).getProfile(request, response) match {
      case None => None
      case Some(p) => p.getProperty("department")
    }
  }

  private def getProfileStdTypeIds: Option[String] = {
    new EmsCookieHelper(entityDao).getProfile(request, response) match {
      case None => None
      case Some(p) => p.getProperty("stdType")
    }
  }

  protected def getSemester(using project: Project): Semester = {
    SemesterHelper.getSemester(entityDao, semesterService, project, request, response)
  }

  protected def getUser(using project: Project): User = {
    entityDao.findBy(classOf[User], "code" -> Securities.user, "school" -> project.school).head
  }

  protected def getUserOf[A](clazz: Class[A]): A = {
    getInt("project.id") match {
      case None =>
        val builder = OqlBuilder.from(clazz, "s")
        builder.where("s.code=:code", Securities.user)
        val stds = entityDao.search(builder)
        if (stds.size == 1) {
          stds.head
        } else {
          throw new RuntimeException("find more than one user for:" + Securities.user + ", project param needed.")
        }
      case Some(projectId) =>
        val builder = OqlBuilder.from(clazz, "s")
        builder.where("s.project.id=:projectId and s.code=:code", projectId, Securities.user)
        entityDao.search(builder).head
    }
  }

  private def getFirstProject(): Project = {
    val query = OqlBuilder.from(classOf[Project], "p")
    query.where("p.endOn is null or p.endOn > :today", LocalDate.now)
    query.orderBy("p.code").cacheable()
    entityDao.search(query).head
  }
}
