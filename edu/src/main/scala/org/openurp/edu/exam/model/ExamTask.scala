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
import org.beangle.data.model.pojo.{Coded, Remark}
import org.openurp.base.model.{Department, SemesterBased}
import org.openurp.base.resource.model.{Building, Classroom}
import org.openurp.code.asset.model.ClassroomType
import org.openurp.code.edu.model.ExamType

import java.time.LocalDate

/**
 * 排考任务
 */
class ExamTask extends LongId, Coded, SemesterBased, Remark {

  /** 考试类型 */
  var examType: ExamType = _

  /** 开课院系 */
  var teachDepart: Department = _

  /** 排考组 */
  var group: Option[ExamGroup] = None

  /** 排考课程 */
  var activities = Collections.newBuffer[ExamActivity]

  /** 考生人数 */
  var stdCount: Int = _

  /** 考试日期 */
  var examOn: Option[LocalDate] = None

  /** 开始时间 */
  var beginAt: HourMinute = _

  /** 结束时间 */
  var endAt: HourMinute = _

  /** 时间已经指定 */
  var timeAllotted: Boolean = false

  /** 教学楼 */
  var building: Option[Building] = None

  /** 教室组 */
  var roomGroup: Option[ExamRoomGroup] = None

  /** 教室列表 */
  var rooms = Collections.newBuffer[Classroom]

  /** 教室类型 */
  var roomType: Option[ClassroomType] = None

  /** 考试时长 */
  var duration: Short = _

  /** 考试周 */
  var examWeek: Option[Short] = None

  /** 最早考试日期 */
  var minExamOn: Option[LocalDate] = None

  /** 是否集中安排 */
  var centralized: Boolean = _

  /** 与上课冲突上限 */
  var maxCourseConflictRatio: Option[Float] = None

}
