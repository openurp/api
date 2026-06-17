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

import jakarta.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.beangle.data.dao.EntityDao
import org.beangle.ems.app.web.EmsCookie
import org.beangle.webmvc.context.Params
import org.openurp.base.model.{Project, Semester}
import org.openurp.base.service.SemesterService

import java.time.LocalDate

object SemesterHelper {
  def getSemester(entityDao: EntityDao, semesterService: SemesterService,
                  project: Project, request: HttpServletRequest, response: HttpServletResponse): Semester = {
    val cookie = EmsCookie.get(request, response)
    Params.getInt("semester.id") match {
      case None =>
        cookie.data.get("semester") match
          case None => semesterService.get(project, LocalDate.now)
          case Some(semesterId) => entityDao.get(classOf[Semester], semesterId.toInt)
      case Some(id) =>
        val semester = entityDao.get(classOf[Semester], id)
        if (!cookie.data.contains("semester") || semester.id != cookie.data("semester").toInt) {
          cookie.data.put("semester", semester.id.toString)
          EmsCookie.update(request, response, cookie, true)
        }
        semester
    }
  }
}
