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
import org.beangle.data.model.pojo.Remark
import org.openurp.base.model.Semester
import org.openurp.code.asset.model.ClassroomType
import org.openurp.code.edu.model.{ExamForm, ExamType}
import org.openurp.edu.clazz.model.Clazz

import java.time.LocalDate
import scala.collection.mutable

/** 考试活动
 */
class ExamActivity extends LongId, Remark {
  /** 考试类型 */
  var examType: ExamType = _

  /** 教学任务 */
  var clazz: Clazz = _

  /** 排考任务 */
  var task: Option[ExamTask] = _

  /** 考试学期 */
  var semester: Semester = _

  /** 考试日期 */
  var examOn: Option[LocalDate] = None

  /** 开始时间 */
  var beginAt: HourMinute = HourMinute.Zero

  /** 结束时间 */
  var endAt: HourMinute = HourMinute.Zero

  /** 考试方式 */
  var examForm: Option[ExamForm] = None

  /** 考试时长 */
  var examDuration: Short = _

  /** 考试教室类型 */
  var roomType: Option[ClassroomType] = None

  /** 学生人数 */
  var stdCount: Int = _

  /** 考场列表 */
  var rooms: collection.mutable.Buffer[ExamRoom] = _

  /** 应考学生 */
  var examTakers: mutable.Set[ExamTaker] = Collections.newSet[ExamTaker]

  /** 考试周 */
  var examWeek: Option[Int] = None

  /** 是否集中安排 */
  var centralized: Option[Boolean] = None

  /** 试卷编号 */
  var examPaperNo: Option[String] = None

  /** 发布状态 */
  var publishState: PublishState = _
}
