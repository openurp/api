/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.room.model

import org.beangle.commons.lang.time.WeekTime
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.Room
import org.openurp.code.edu.model.ActivityType

/**
 * 房间占用情况
 * 这里不用classroom的原因，在于跨项目的教室冲突检测需要。
 */
class Occupancy extends LongId with Updated {

  /** 房间 */
  var room: Room = _ // 教室/考场/活动场地

  /** 时间 */
  var time = new WeekTime // 时间安排

  /** 活动类型 */
  var activityType: ActivityType = _

  /**用户系统*/
  var userApp: UserApp = _

  /** 活动ID */
  var activityId: Long = _

  /** 说明 */
  var comments: String = _
}
