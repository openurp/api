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

import org.beangle.data.model.IntId
import org.beangle.data.model.pojo.*
import org.openurp.base.model.{Campus, Department, School}
import org.openurp.base.resource.model.Building
import org.openurp.code.asset.model.{BuildingType, RoomType}

/**
 * 房间
 */
class Room extends IntId, Coded, Named, TemporalOn, Updated, Remark {

  /** 所属学校 */
  var school: School = _

  /** 所属校区 */
  var campus: Campus = _

  /** 管理部门 */
  var department: Option[Department] = None

  /** 所属建筑 */
  var building: Option[Building] = None

  /** 房间类型 */
  var roomType: RoomType = _

  /** 楼层 */
  var floorNo: Int = _

}
