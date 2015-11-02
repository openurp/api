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

import scala.collection.mutable.{ Buffer, ListBuffer }

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.LongId
import org.openurp.base.model.{ Department, Semester }
import org.openurp.edu.base.model.Classroom
import org.openurp.edu.teach.lesson.model.Lesson
import org.openurp.hr.base.model.Staff

/**
 * 考场
 *
 * @author chaostone
 */
class ExamRoom extends LongId {

  /**考试学期*/
  var semester: Semester = _

  /**考试日期*/
  var examOn: Date = _

  /**开始时间*/
  var beginAt: HourMinute = _

  /**结束时间*/
  var endAt: HourMinute = _

  /**教室*/
  var classroom: Classroom = _

  /**主考教师*/
  var examiner: Staff = _

  /**主考教师院系*/
  var department: Department = _

  /**考试活动*/
  var activities: Buffer[ExamActivity] = new ListBuffer[ExamActivity]

  /**监考信息*/
  var monitors: Buffer[ExamMonitor] = new ListBuffer[ExamMonitor]

  /**教室借用ID*/
  var roomApplyId: java.lang.Long = _

  def lessons: Set[Lesson] = {
    activities.map(a => a.lesson).toSet
  }

  def this(activity: ExamActivity, classroom: Classroom) {
    this()
    this.classroom = classroom
    this.activities += activity
    activity.rooms.asInstanceOf[Buffer[ExamRoom]] += this
  }

}