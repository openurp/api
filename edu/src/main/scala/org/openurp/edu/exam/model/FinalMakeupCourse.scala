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
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.*
import org.openurp.base.resource.model.Classroom
import org.openurp.base.std.model.Squad

import java.time.{Instant, LocalDate}
import scala.collection.mutable

/**
 * 毕业补考任务
 *
 */
class FinalMakeupCourse extends LongId {

  /** 补考序号 */
  var crn: String = _

  /** 项目 */
  var project: Project = _

  /** 学年学期 */
  var semester: Semester = _

  /** 课程 */
  var course: Course = _

  /** 校区 */
  var campus: Campus = _

  /** 开课院系 */
  var depart: Department = _

  /** 清考名单 */
  var takers: mutable.Buffer[FinalMakeupTaker] = Collections.newBuffer[FinalMakeupTaker]

  /** 行政班列表 */
  var squads: collection.mutable.Set[Squad] = Collections.newSet[Squad]

  /** 学生人数 */
  var stdCount: Int = _

  /** 是否提交成绩 */
  var status: Int = _

  /** 阅卷老师 */
  var teacher: Option[Teacher] = None

  /** 成绩录入时间 */
  var inputAt: Option[Instant] = None

  /** 考试日期 */
  var examOn: Option[LocalDate] = None

  /** 开始时间 */
  var beginAt: HourMinute = HourMinute.Zero

  /** 结束时间 */
  var endAt: HourMinute = HourMinute.Zero

  /** 教室 */
  var room: Option[Classroom] = None

  /** 监考人1 */
  var invigilator1: Option[User] = None

  /** 监考人2 */
  var invigilator2: Option[User] = None

  def mergeWith(makeupClazz: FinalMakeupCourse): Unit = {
    stdCount += makeupClazz.stdCount
    squads ++= makeupClazz.squads
    for (taker2 <- makeupClazz.takers) {
      taker2.makeupCourse = Some(this)
      this.takers += taker2
    }
  }
}
