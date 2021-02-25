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
package org.openurp.qos.evaluation.department.model

import org.beangle.data.model.LongId
import org.openurp.base.model.Department
import org.openurp.base.edu.model.Semester
import org.beangle.commons.collection.Collections
import org.openurp.base.edu.model.Teacher
import org.openurp.qos.evaluation.model.Questionnaire

import java.time.Instant

/**
 * 院系问卷评教结果
 *
 * @author chaostone
 */
class DepartEvaluate extends LongId {

  /**学年学期*/
  var semester: Semester = _
  /** 教师 */
  var teacher: Teacher = _
  /** 开课院系 */
  var department: Department = _
  /** 问卷信息 */
  var questionnaire: Questionnaire = _
  /** 问题评教结果 */
  var questionResults = Collections.newSet[DepartQuestion]
  /** 评教时间 */
  var evaluateAt: Instant = _
  /**备注*/
  var remark: String = _

  var totalScore: java.lang.Float = _

  def calTotalScore(): Unit = {
    this.totalScore = this.questionResults.foldLeft(0f)(_ + _.score)

  }
}
