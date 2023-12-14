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
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student
import org.openurp.edu.clazz.model.Clazz

/** 学生出勤统计
 */
class Attendance extends LongId {

  /** 学生 */
  var std: Student = _

  /** 课程 */
  var clazz: Clazz = _

  /** 学年学期 */
  var semester: Semester = _

  /** 实到次数 */
  var present: Short = _

  /** 缺席次数（包括旷课、请假） */
  var absent: Short = _

  /** 请假次数 */
  var leave: Short = _

  /** 迟到早退次数 */
  var late: Short = _

  /** 出勤状态 */
  var states: AttendStates = AttendStates.Empty
}
