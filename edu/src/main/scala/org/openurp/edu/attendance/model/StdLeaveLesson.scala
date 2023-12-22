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

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.LongId
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student
import org.openurp.edu.clazz.model.Clazz

import java.time.LocalDate

/**
 * 学生课程请假记录
 */
class StdLeaveLesson extends LongId {

  /** 学生 */
  var std: Student = _

  /** 请假学期 */
  var semester: Semester = _

  /** 请假的课程 */
  var clazz: Clazz = _

  /** 请假类型 */
  var leaveType: LeaveType = _

  /** 上课日期 */
  var lessonOn: LocalDate = _

  /** 上课时间 */
  var lessonTime: String = _

  /** 请假申请 */
  var leave: Option[StdLeave] = None
}
