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
import org.beangle.webmvc.context.Params
import org.beangle.webmvc.support.{ActionSupport, ServletSupport}
import org.beangle.webmvc.view.View
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.{Project, Semester, User}
import org.openurp.base.service.{Feature, ProjectConfigService, SemesterService}
import org.openurp.code.Code
import org.openurp.code.service.CodeService

abstract class TeacherSupport extends ActionSupport, ServletSupport {

  var entityDao: EntityDao = _
  var codeService: CodeService = _
  var semesterService: SemesterService = _
  var configService: ProjectConfigService = _

  def index(): View = {
    val teacher = getTeacher
    if (null == teacher) {
      forward("not-teacher")
    } else {
      if teacher.projects.isEmpty then
        forward("empty-project")
      else
        val projects = teacher.projects.toList.sortBy(_.code)
        val projectId = getInt("projectId", projects.head.id)

        given project: Project = teacher.projects.find(_.id == projectId).getOrElse(projects.head)

        put("projects", projects)
        put("project", project)
        put("teacher", teacher)
        projectIndex(teacher)
    }
  }

  protected def projectIndex(teacher: Teacher)(using project: Project): View = {
    forward()
  }

  protected final def getSemester: Semester = {
    SemesterHelper.getSemester(entityDao, semesterService, getProject, request, response)
  }

  protected final def getProject: Project = {
    val project = request.getAttribute("project")
    if (null != project) project.asInstanceOf[Project]
    else
      Params.getId("project", classOf[Int]) match {
        case Some(projectId) =>
          val project = entityDao.get(classOf[Project], projectId)
          request.setAttribute("project", project)
          project
        case None => null
      }
  }

  protected final def getTeacher: Teacher = {
    val teacher = request.getAttribute("teacher")
    if (null != teacher) teacher.asInstanceOf[Teacher]
    else {
      val teachers = entityDao.findBy(classOf[Teacher], "staff.code" -> Securities.user)
      teachers.foreach { t => request.setAttribute("teacher", t) }
      teachers.headOption.orNull
    }
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
