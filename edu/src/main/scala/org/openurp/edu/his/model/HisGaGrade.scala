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

package org.openurp.edu.his.model

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.archive
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.model.ArchivedByYear
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{GradeType, GradingMode}
import org.openurp.edu.grade.model.{GaGrade, Grade}

/** 归档总评成绩
 */
@archive
class HisGaGrade extends LongId, Grade, Updated, Remark, ArchivedByYear {
  /** 成绩类型 */
  var gradeType: GradeType = _
  /** 成绩记录方式 */
  var gradingMode: GradingMode = _
  /** 得分 */
  var score: Option[Float] = None
  /** 得分字面值 */
  var scoreText: Option[String] = None
  /** 对应的课程成绩 */
  var courseGrade: HisCourseGrade = _
  /** 成绩状态 */
  var status: Int = _
  /** 是否通过 */
  var passed: Boolean = _
  /** 操作者 */
  var operator: Option[String] = None
  /** 绩点 */
  var gp: Option[Float] = None
  /** 加减修正分 */
  var delta: Option[Float] = None

  def std: Student = courseGrade.std

  def convert(): GaGrade = {
    val gg = new GaGrade()
    gg.gradeType = gradeType
    gg.gradingMode = gradingMode
    gg.score = score
    gg.scoreText = scoreText
    gg.status = status
    gg.passed = passed
    gg.operator = operator
    gg.gp = gp
    gg.delta = delta
    gg
  }
}
