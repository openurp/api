/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.base.model

import org.beangle.data.model.{ Coded, IntId, Named, Remark, TemporalOn, Updated }
import org.openurp.code.asset.model.RoomType

/**
 * 校区
 */
class Campus extends IntId with Cloneable with Coded with Named with TemporalOn with Updated with Remark {
  var enName: String = _
  var shortName: String = _
}

/**
 * 建筑
 */
class Building extends IntId with Coded with Named with TemporalOn with Updated with Remark {
  /**
   * 所属校区
   */
  var campus: Campus = _
  var enName: String = _
  var shortName: String = _
}

/**
 * 房间
 */
class Room extends IntId with Coded with Named with TemporalOn with Updated with Remark {

  /**
   * 管理部门
   */
  var department: Department = _

  /**
   * 所属校区
   */
  var campus: Campus = _

  /**
   * 所属建筑
   */
  var building: Building = _

  /**房间类型*/
  var roomType:RoomType=_
  /**
   * 楼层
   */
  var floor: Int = _
  /**
   * 容量
   */
  var capacity: Int = _

}
