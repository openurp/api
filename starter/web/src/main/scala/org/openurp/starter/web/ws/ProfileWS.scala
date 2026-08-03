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

import org.beangle.commons.collection.Collections
import org.beangle.commons.json.{Json, JsonArray, JsonObject}
import org.beangle.security.context.{RunAs, SecurityContext}
import org.beangle.security.web.{CookieKeys, ProfileCookie}
import org.beangle.web.servlet.util.CookieUtils
import org.beangle.webmvc.annotation.response
import org.beangle.webmvc.context.ActionContext
import org.beangle.webmvc.support.ActionSupport

class ProfileWS extends ActionSupport {

  @response
  def index(): Json = {
    val json = new JsonObject()
    val session = SecurityContext.get.session.get
    var profileId: Option[String] = None
    val parray = new JsonArray()
    val account = session.principal
    if (null != account.profiles) {
      val response = ActionContext.current.response
      val request = ActionContext.current.request

      var profiles = account.profiles.toSeq
      val runAsJson = CookieUtils.getCookieValue(request, CookieKeys.RunAsKey)
      if (null != runAsJson) {
        RunAs.parseJson(runAsJson) foreach { r => profiles = r.profiles }
      }
      // cookie / URL 参数 → 校验是否在可用列表中；无则回落到第一个 profile
      val resolvedProfileId = ProfileCookie.check(profiles, ProfileCookie.get(request, response).getOrElse(""))
      resolvedProfileId foreach { pid =>
        ProfileCookie.update(request, response, pid, true)
        profileId = Some(pid)
      }
      val sb = Collections.newBuffer[String]
      profiles foreach { profile =>
        val p = new JsonObject()
        p.add("id", profile.id)
        p.add("name", profile.name)
        p.add("properties", new JsonObject(profile.properties))
        parray.add(p)
      }
      json.add("profiles", parray)
      profileId foreach { pid =>
        json.add("profileId", pid)
      }
    }
    json
  }
}
