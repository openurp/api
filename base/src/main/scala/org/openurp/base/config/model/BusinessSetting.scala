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

package org.openurp.base.config.model

import org.beangle.commons.json.JsonObject
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.ProjectBased

/** 业务设置 */
@config
class BusinessSetting extends LongId, ProjectBased, Updated {

  /** 业务类型 */
  var business: String = _

  /** 业务配置ID */
  var profileId: String = "default"

  /** 设置JSON */
  var settings: JsonObject = _

}
