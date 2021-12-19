/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.qos.evaluation.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.edu.model.{Student, Teacher}
import org.openurp.base.model.Department
import org.openurp.edu.clazz.model.Clazz
import org.openurp.qos.evaluation.model.Questionnaire

import java.time.Instant

/**
 * 问卷评教结果
 *
 * @author chaostone
 */
class EvaluateResult extends LongId with Updated{
  /** 教学任务 */
  var clazz: Clazz = _
  /** 教师 */
  var teacher: Teacher = _
  /** 学生 */
  var student: Student = _
  /** 开课院系 */
  var department: Department = _
  /** 问卷信息 */
  var questionnaire: Questionnaire = _
  /** 问题评教结果 */
  var questionResults = Collections.newSet[QuestionResult]
  /**问卷状态 */
  /**
   * 1正常 2 无效 3异常(互斥)
   * 总数=有效问卷+无效问卷+异常问卷        无效问卷=无效比例*(总数-异常问卷)
   */
  var statType: Int = _
  /**总分*/
  var score :Float = _
}
