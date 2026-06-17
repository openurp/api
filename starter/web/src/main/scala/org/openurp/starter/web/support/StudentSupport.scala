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

import org.beangle.data.dao.EntityDao
import org.beangle.security.Securities
import org.beangle.webmvc.support.{ActionSupport, ServletSupport}
import org.beangle.webmvc.view.View
import org.openurp.base.model.{Project, Semester, User}
import org.openurp.base.service.{Feature, ProjectConfigService, SemesterService}
import org.openurp.base.std.model.Student
import org.openurp.code.Code
import org.openurp.code.service.CodeService

abstract class StudentSupport extends ActionSupport, ServletSupport {

  var entityDao: EntityDao = _

  var semesterService: SemesterService = _

  var codeService: CodeService = _

  var configService: ProjectConfigService = _

  final def index(): View = {
    var stds = getStudents
    if stds.isEmpty then
      forward("not-student")
    else
      val stdId = getLong("studentId").getOrElse(stds.head.id)
      val student = stds.find(_.id == stdId).getOrElse(stds.head)
      put("students", stds)
      put("student", student)
      put("project", student.project)
      projectIndex(student)
  }

  protected def projectIndex(student: Student): View = {
    forward()
  }

  protected final def getSemester: Semester = {
    SemesterHelper.getSemester(entityDao, semesterService, getProject, request, response)
  }

  protected final def getProject: Project = {
    val project = request.getAttribute("project")
    if (null != project) project.asInstanceOf[Project]
    else getStudent.project
  }

  protected final def getStudents: Seq[Student] = {
    entityDao.findBy(classOf[User], "code" -> Securities.user).headOption match
      case None => List.empty
      case Some(user) =>
        var stds = entityDao.findBy(classOf[Student], "user" -> user)
        if (stds.size > 1) {
          val actives = stds.filter(_.project.active)
          if actives.nonEmpty then stds = actives
          stds = stds.sortBy(_.beginOn).reverse
        }
        stds
  }

  protected final def getStudent: Student = {
    val std = request.getAttribute("student")
    if (null != std) std.asInstanceOf[Student]
    else
      val stds = getStudents
      if (stds.isEmpty) throw new RuntimeException(s"${Securities.user} is not a student")
      val student = getLong("projectId") match {
        case None =>
          val stdId = getLong("studentId").getOrElse(stds.head.id)
          stds.find(_.id == stdId).getOrElse(stds.head)
        case Some(projectId) =>
          stds.find(_.project.id == projectId).getOrElse(stds.head)
      }
      request.setAttribute("student", student)
      request.setAttribute("project", student.project)
      student
  }

  protected final def getUser: User = {
    entityDao.findBy(classOf[User], "code" -> Securities.user).head
  }

  def getCodes[T <: Code](clazz: Class[T])(using project: Project): collection.Seq[T] = {
    codeService.get(clazz)
  }

  def getCode[T <: Code](clazz: Class[T], id: Int): T = {
    codeService.get(clazz, id)
  }

  protected def getConfig[T](name: String, defaultValue: T)(using project: Project): T = {
    configService.get(project, name, defaultValue)
  }

  protected def getConfig(f: Feature)(using project: Project): Any = {
    configService.get[Any](project, f)
  }
}
