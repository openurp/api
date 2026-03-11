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

package org.openurp.base.config.service

import org.beangle.commons.json.{JsonMapper, JsonObject}
import org.openurp.base.config.model.BusinessSetting
import org.openurp.base.model.Project

trait BusinessSettingService {

  def getSetting[T](project: Project, business: String, profileId: String, mapper: JsonMapper[T]): Option[T]

  def update(project: Project, business: String, profileId: String, settings: JsonObject): Unit

  def get(project: Project, business: String, profileId: String): Option[BusinessSetting]
}
