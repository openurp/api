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

package org.openurp.edu.room.config

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.IntId
import org.beangle.data.model.annotation.config
import org.openurp.base.model.School

/** 教室借用设置
 */
@config
class RoomApplySetting extends IntId {

  var school: School = _

  /** 申请提前量 */
  var daysBeforeApply: Int = _

  /** 借用须知 */
  var notice: Option[String] = None

  /** 开始时间 */
  var beginAt: HourMinute = HourMinute.Zero

  /** 结束时间 */
  var endAt: HourMinute = HourMinute.Zero

  /** 是否开放 */
  var opened: Boolean = _
}
