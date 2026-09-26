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
import org.beangle.data.model.pojo.{Named, Updatable}
import org.openurp.base.model.SemesterBased
import org.openurp.base.resource.model.Classroom
import org.openurp.code.edu.model.ExamType
import org.openurp.edu.exam.config.ExamAllocSetting

import java.time.LocalDate
import scala.collection.mutable
import scala.compiletime.uninitialized

/** 排考组 */
class ExamGroup extends LongId, Named, SemesterBased, Updatable {

  /** 考试类型 */
  var examType: ExamType = uninitialized

  /** 开始日期 */
  var beginOn: LocalDate = uninitialized

  /** 结束日期 */
  var endOn: LocalDate = uninitialized

  /** 场次列表 */
  var turns: mutable.Buffer[ExamTurn] = Collections.newBuffer[ExamTurn]

  /** 允许随堂考试 */
  var allowInClass: Boolean = uninitialized

  /** 最小学生上课冲突人数 */
  var minCourseConflictCount: Int = uninitialized

  /** 最大学生上课冲突比率 */
  var maxCourseConflictRatio: Float = uninitialized

  /** 发布状态 */
  var publishState: PublishState = uninitialized

  /** 排考任务列表 */
  var tasks = Collections.newBuffer[ExamTask]

  /** 可用教室 */
  var rooms = Collections.newBuffer[Classroom]

  /** 教室分配设置 */
  var allocSetting: ExamAllocSetting = uninitialized

  def getTurn(examOn: LocalDate, beginAt: HourMinute): ExamTurn = {
    this.turns.find(et => (et.examOn == examOn) && (et.beginAt == beginAt)).get
  }
}
