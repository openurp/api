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

package org.openurp.edu.attendance.model

import org.beangle.data.model.LongId
import org.openurp.edu.clazz.model.Clazz

import java.time.Instant

/** 课程考勤统计 */
class AttendClazzStat extends LongId {

  var clazz: Clazz = _

  /** 考勤次数 */
  var idx: Int = _

  /** 上课开始时间 */
  var beginAt: Instant = _

  /** 实到人数 */
  var present: Short = _

  /** 缺席人数（包括旷课、请假） */
  var absent: Short = _

  /** 请假人数 */
  var leave: Short = _

  /** 迟到早退人数 */
  var late: Short = _

}
