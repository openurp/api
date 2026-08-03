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

package org.openurp.starter.web.ws.std

import org.beangle.commons.json.Json
import org.beangle.data.json.JsonAPI
import org.beangle.data.json.JsonAPI.Context
import org.beangle.webmvc.annotation.response
import org.beangle.webmvc.context.ActionContext
import org.openurp.base.model.{Calendar, Semester}
import org.openurp.base.service.SemesterService
import org.openurp.starter.web.support.{StudentSupport, StudentWSSupport}

/** 查询学生在籍期间的学年学期
 */
class SemesterWS extends StudentWSSupport {

  @response
  def index(): Json = {
    val std = getStudent
    val (his, cur) = semesterService.get(std.project, std.beginOn, std.endOn)

    given context: Context = JsonAPI.context(ActionContext.current.params)

    context.filters.include(classOf[Semester], "id", "code", "name", "schoolYear", "beginOn", "endOn", "archived", "calendar")
    context.filters.include(classOf[Calendar], "id", "code", "name", "firstWeekday")

    val semesters = his ++ cur
    JsonAPI.newJson(semesters.map(JsonAPI.create(_, "")))
  }

}
