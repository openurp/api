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
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.{Project, User}
import org.openurp.base.service.{Feature, ProjectConfigService, SemesterService}
import org.openurp.code.Code
import org.openurp.code.service.CodeService

abstract class TeacherSupport extends ActionSupport, ServletSupport {

  var entityDao: EntityDao = _
  var codeService: CodeService = _
  var semesterService: SemesterService = _
  var configService: ProjectConfigService = _

  protected final def getTeacher: Teacher = {
    val teacher = request.getAttribute("teacher")
    if (null != teacher) teacher.asInstanceOf[Teacher]
    else {
      val teachers = entityDao.findBy(classOf[Teacher], "staff.code" -> Securities.user)
      teachers.foreach { t => request.setAttribute("teacher", t) }
      teachers.headOption.orNull
    }
  }

  protected final def getProject: Project = {
    val project = entityDao.get(classOf[Project], getIntId("project"))
    if(null==project){
      error("wrong project id")
    }else{
      project
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
