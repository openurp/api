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

package org.openurp.starter.web.ws

import org.beangle.commons.json.JsonObject
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.beangle.data.json.JsonAPI
import org.beangle.data.json.JsonAPI.Context
import org.beangle.security.Securities
import org.beangle.security.context.SecurityContext
import org.beangle.webmvc.annotation.response
import org.beangle.webmvc.context.ActionContext
import org.beangle.webmvc.support.ActionSupport
import org.openurp.base.edu.model.Major
import org.openurp.base.model.{Project, School}
import org.openurp.base.std.model.Student

/** 提供学生个人信息
 */
class StudentWS extends ActionSupport {
  var entityDao: EntityDao = _

  @response
  def index(): JsonObject = {
    val q = OqlBuilder.from(classOf[Student], "std")
    q.where("std.user.code=:code", Securities.user)
    q.orderBy("std.endOn desc")
    SecurityContext.get.profile foreach { p =>
      q.where("std.project.id=:projectId", p.id.intValue())
    }
    val stds = entityDao.search(q)

    given context: Context = JsonAPI.context(ActionContext.current.params)

    context.filters.exclude(classOf[Any], "createdAt", "updatedAt", "operator")
    context.filters.include(classOf[Student], "id", "code", "name", "project", "beginOn", "major")
    context.filters.include(classOf[Project], "id", "code", "name", "minor", "school")
    context.filters.include(classOf[Major], "id", "code", "name")
    context.filters.include(classOf[School], "code", "name", "logoUrl")
    JsonAPI.newJson(stds.map(JsonAPI.create(_, "")))
  }

}
