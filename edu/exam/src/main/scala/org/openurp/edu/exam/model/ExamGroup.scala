/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.exam.model

import java.time.LocalDate

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Named
import org.beangle.data.model.pojo.Updated
import org.openurp.edu.base.SemesterBased
import org.openurp.edu.base.model.Classroom
import org.openurp.code.edu.model.ExamType

/**排考组*/
class ExamGroup extends LongId with Named with SemesterBased with Updated {

  /** 考试类型 */
  var examType: ExamType = _

  /** 开始日期 */
  var beginOn: LocalDate = _

  /** 结束日期 */
  var endOn: LocalDate = _

  /** 场次列表*/
  var turns = Collections.newBuffer[ExamTurn]

  /** 允许随堂考试*/
  var allowInClass: Boolean = _

  /**最小学生上课冲突人数*/
  var minCourseConflictCount: Int = _

  /**最大学生上课冲突比率*/
  var maxCourseConflictRatio: Float = _

  /**发布状态*/
  var state: PublishState.State = _

  /** 排考任务列表*/
  var tasks = Collections.newBuffer[ExamTask]

  /** 可用教室*/
  var rooms = Collections.newBuffer[Classroom]

  /**教室分配设置*/
  var allocSetting: RoomAllocSetting = _
}
