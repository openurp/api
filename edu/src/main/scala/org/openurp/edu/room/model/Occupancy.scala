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

package org.openurp.edu.room.model

import org.beangle.commons.lang.time.WeekTime
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.resource.model.Classroom
import org.openurp.code.edu.model.ActivityType

/**
 * 房间占用情况
 */
class Occupancy extends LongId with Updated {

  /** 房间 */
  var room: Classroom = _

  /** 时间 */
  var time = new WeekTime

  /** 活动类型 */
  var activityType: ActivityType = _

  /**用户系统*/
  var app: RoomOccupyApp = _

  /** 活动ID */
  var activityId: Long = _

  /** 说明 */
  var comments: String = _
}
