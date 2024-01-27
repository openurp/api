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

package org.openurp.base.resource.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.*
import org.openurp.base.model.School
import org.openurp.code.asset.model.DeviceType

/** 房间内的设备
 */
class Device extends LongId, Coded, Named, Updated, Remark, TemporalOn {

  /** 学校 */
  var school: School = _

  var deviceType: DeviceType = _

  var ip: Option[String] = None

  var uuid: Option[String] = None

  var room: Option[Classroom] = None
}
