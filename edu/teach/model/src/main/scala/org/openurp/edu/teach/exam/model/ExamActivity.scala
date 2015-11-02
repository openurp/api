/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.teach.exam.model

import java.sql.Date

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.LongId
import org.openurp.base.model.Semester
import org.openurp.edu.teach.code.model.ExamType
import org.openurp.edu.teach.lesson.model.Lesson

/**
 * 考试活动 </p>
 *
 * @depend - - - Semester
 * @depend - - - ExamType
 * @depend - - - Course
 * @has 1..* AssignedTo 1..* Lesson
 * @depend - - - Classroom
 * @depend - - - Teacher
 * @depend - - - Department
 * @depend - - - ExamMonitor
 * @composed 1 has * ExamTake
 */

class ExamActivity extends LongId {
  /** 考试类型 */
  var examType: ExamType = _

  /** 教学任务 */
  var lesson: Lesson = _

  /**考试日期*/
  var examOn: Date = _

  /**考试学期*/
  var semester: Semester = _

  /**开始时间*/
  var beginAt: HourMinute = _

  /**结束时间*/
  var endAt: HourMinute = _

  /** 备注 */
  var remark: String = _

  /** 考场列表 */
  var rooms: collection.mutable.Buffer[ExamRoom] = _

}