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
import org.beangle.data.model.pojo.Updatable
import org.openurp.base.model.Department
import org.openurp.base.resource.model.Classroom
import org.openurp.code.edu.model.ActivityType
import scala.compiletime.uninitialized

/**
 * 房间占用情况
 */
class Occupancy extends LongId, Updatable {

  /** 房间 */
  var room: Classroom = uninitialized

  /** 时间 */
  var time = new WeekTime

  /** 活动类型 */
  var activityType: ActivityType = uninitialized

  /** 用户系统 */
  var app: RoomOccupyApp = uninitialized

  /** 活动ID */
  var activityId: Long = uninitialized

  /** 活动主题 */
  var subject: String = uninitialized

  /** 占用院系 */
  var depart: Department = uninitialized

  /** 是否可以共享占用 */
  var shared: Boolean = uninitialized

  /** 学生人数 */
  var stdCount: Int = uninitialized
}
