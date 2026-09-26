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
import scala.compiletime.uninitialized

/** 单词课程考勤统计 */
class LessonAttendance extends LongId {

  var clazz: Clazz = uninitialized

  /** 第几次考勤 */
  var idx: Int = uninitialized

  /** 上课开始时间 */
  var beginAt: Instant = uninitialized

  /** 实到人数 */
  var present: Short = uninitialized

  /** 缺席人数（包括旷课、请假） */
  var absent: Short = uninitialized

  /** 请假人数 */
  var leave: Short = uninitialized

  /** 迟到早退人数 */
  var late: Short = uninitialized

}
