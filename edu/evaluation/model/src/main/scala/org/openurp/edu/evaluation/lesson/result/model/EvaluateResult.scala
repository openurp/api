/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.evaluation.lesson.result.model

import scala.collection.mutable.HashSet

import org.beangle.data.model.LongId
import org.openurp.base.model.Department
import org.openurp.edu.base.model.Student
import org.openurp.edu.evaluation.model.Questionnaire
import org.openurp.edu.lesson.model.Lesson
import org.openurp.hr.base.model.Staff
/**
 * 问卷评教结果
 *
 * @author chaostone
 */
class EvaluateResult extends LongId {
  /** 教学任务 */
  var lesson: Lesson = _
  /** 教师 */
  var staff: Staff = _
  /** 学生 */
  var student: Student = _
  /** 开课院系 */
  var department: Department = _
  /** 问卷信息 */
  var questionnaire: Questionnaire = _
  /** 问题评教结果 */
  var questionResults: HashSet[QuestionResult] = new collection.mutable.HashSet[QuestionResult]
  /** 评教时间 */
  var evaluateAt: java.util.Date = _
  /**问卷状态 */
  /**
   * 1正常 2 无效 3异常(互斥)
   * 总数=有效问卷+无效问卷+异常问卷        无效问卷=无效比例*(总数-异常问卷)
   */
  var statType: Int = _
  /**备注*/
  var remark: String = _
}