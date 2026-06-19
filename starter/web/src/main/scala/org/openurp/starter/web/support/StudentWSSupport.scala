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
import org.openurp.base.model.{Project, User}
import org.openurp.base.service.{Feature, ProjectConfigService, SemesterService}
import org.openurp.base.std.model.Student
import org.openurp.code.Code
import org.openurp.code.service.CodeService

abstract class StudentWSSupport extends ActionSupport, ServletSupport {

  var entityDao: EntityDao = _

  var semesterService: SemesterService = _

  var codeService: CodeService = _

  var configService: ProjectConfigService = _

  protected final def getStudent: Student = {
    val stdId = getLongId("student")
    if (stdId == 0) {
      throw new IllegalArgumentException("cannot find valid std.id param")
    } else {
      val std = entityDao.get(classOf[Student], stdId)
      if (std.user.code != Securities.user) {
        throw new IllegalArgumentException("Invalid std.id params")
      } else {
        std
      }
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
