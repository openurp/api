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

import java.sql.Date
import scala.collection.mutable.{ Buffer, ListBuffer }
import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.LongId
import org.openurp.base.model.{ Department, Semester }
import org.openurp.edu.base.model.Classroom
import org.openurp.edu.base.model.Teacher
import org.beangle.commons.collection.Collections
import org.openurp.edu.course.model.Clazz

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
  var room: Classroom = _

  /**主考教师*/
  var examiner: Option[Teacher] = None

  /**主考教师院系*/
  var department: Option[Department] = None

  /**考试活动*/
  var activities = Collections.newBuffer[ExamActivity]

  /**监考信息*/
  var monitors = Collections.newSet[ExamMonitor]

  /**教室借用ID*/
  var roomApplyId: Option[Long] = None

  def clazzs: Set[Clazz] = {
    activities.map(a => a.clazz).toSet
  }

  def this(activity: ExamActivity, classroom: Classroom) {
    this()
    this.room = classroom
    this.activities += activity
    activity.rooms.asInstanceOf[Buffer[ExamRoom]] += this
  }

}
