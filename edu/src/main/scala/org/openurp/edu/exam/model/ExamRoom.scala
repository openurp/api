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

package org.openurp.edu.exam.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.LongId
import org.openurp.base.edu.model.Course
import org.openurp.base.model.*
import org.openurp.base.resource.model.Classroom
import org.openurp.code.edu.model.ExamType
import org.openurp.edu.clazz.model.Clazz

import java.time.LocalDate
import scala.collection.mutable

/**
 * 考场
 *
 * @author chaostone
 */
class ExamRoom extends LongId, SemesterBased {

  /** 开课院系 */
  var teachDepart: Department = _

  /** 考试类型 */
  var examType: ExamType = _

  /** 考试日期 */
  var examOn: LocalDate = _

  /** 开始时间 */
  var beginAt: HourMinute = _

  /** 结束时间 */
  var endAt: HourMinute = _

  /** 教室 */
  var room: Classroom = _

  /** 考试人数 */
  var stdCount: Int = _

  /** 考试活动 */
  var activities: mutable.Buffer[ExamActivity] = Collections.newBuffer[ExamActivity]

  /** 监考信息 */
  var invigilations: mutable.Set[Invigilation] = Collections.newSet[Invigilation]

  /** 应考学生 */
  var examTakers: mutable.Set[ExamTaker] = Collections.newSet[ExamTaker]

  def clazzes: Set[Clazz] = {
    activities.map(a => a.clazz).toSet
  }

  def courses: Seq[Course] = {
    activities.map(a => a.clazz.course).toSet.toSeq.sortBy(_.code)
  }

  /** 各个课程的考生人数
   *
   * @return
   */
  def courseStdCounts: Map[Course, Int] = {
    examTakers.map(x => (x.clazz.course, 1)).groupBy(_._1).map(x => (x._1, x._2.size))
  }

  def this(activity: ExamActivity, classroom: Classroom) = {
    this()
    this.room = classroom
    this.activities += activity
    activity.rooms += this
  }

  def courseExamTakers: collection.Map[Course, collection.Set[ExamTaker]] = {
    examTakers.groupBy(_.clazz.course)
  }

  def addExamTaker(taker: ExamTaker): Unit = {
    taker.examRoom = Some(this)
    examTakers.addOne(taker)
  }

  /**
   * 把所有的信息克隆一遍<br>
   * 不包括examTakers
   */
  override def clone: AnyRef = {
    val activity = new ExamRoom
    activity.examOn = this.examOn
    activity.beginAt = this.beginAt
    activity.endAt = this.endAt
    activity.semester = this.semester
    activity
  }
}
