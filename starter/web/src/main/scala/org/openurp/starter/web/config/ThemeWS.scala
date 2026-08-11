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

package org.openurp.starter.web.config

import org.beangle.commons.net.http.HttpUtils
import org.beangle.ems.app.Ems
import org.beangle.webmvc.annotation.response
import org.beangle.webmvc.support.ActionSupport
import org.beangle.webmvc.view.View

class ThemeWS extends ActionSupport {
  @response
  def index(): View = {
    raw(HttpUtils.get(s"${Ems.base}/api/platform/config/themes.json").getText)
  }
}
