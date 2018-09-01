/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.exam.model

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.model.Semester
import org.openurp.edu.base.code.model.ExamType
import org.openurp.edu.course.model.Clazz

/**
 * 考试活动 </p>
 *
 * @depend - - - Semester
 * @depend - - - ExamType
 * @depend - - - Course
 * @has 1..* AssignedTo 1..* Clazz
 * @depend - - - Classroom
 * @depend - - - Teacher
 * @depend - - - Department
 * @depend - - - ExamMonitor
 * @composed 1 has * ExamTake
 */

class ExamActivity extends LongId with Remark {
  /** 考试类型 */
  var examType: ExamType = _

  /** 教学任务 */
  var clazz: Clazz = _

  /**考试日期*/
  var examOn: java.sql.Date = _

  /**考试学期*/
  var semester: Semester = _

  /**开始时间*/
  var beginAt: HourMinute = _

  /**结束时间*/
  var endAt: HourMinute = _

  /** 考场列表 */
  var rooms: collection.mutable.Buffer[ExamRoom] = _

  var state: PublishState.State = _

  def startOn: java.sql.Date = {
    examOn
  }

}
