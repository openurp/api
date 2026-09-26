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

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.InstantRange
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student

import scala.collection.mutable
import scala.compiletime.uninitialized

/** 学生请假申请
 */
class StdLeave extends LongId, InstantRange {

  /** 学年学期 */
  var semester: Semester = uninitialized

  /** 请假类型 */
  var leaveType: LeaveType = uninitialized

  /** 学生 */
  var std: Student = uninitialized

  /** 请假事由 */
  var reason: String = uninitialized

  /** 影响上课次数 */
  var lessons: Int = uninitialized

  /** 请假天数 */
  var days: Int = uninitialized

  /** 请假附件 */
  var files: mutable.Buffer[StdLeaveFile] = Collections.newBuffer[StdLeaveFile]
}
