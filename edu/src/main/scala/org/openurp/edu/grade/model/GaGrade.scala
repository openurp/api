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

package org.openurp.edu.grade.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{GradeType, GradingMode}
import org.openurp.edu.grade.model.Grade

/**
 * 总评成绩
 * </p>
 * 期末总评成绩,补考总评成绩
 *
 * @author chaostone
 * @since 2005
 */
class GaGrade extends LongId, Grade, Updated, Remark {
  /** 成绩类型 */
  var gradeType: GradeType = _
  /** 成绩记录方式 */
  var gradingMode: GradingMode = _
  /** 得分 */
  var score: Option[Float] = None
  /** 得分字面值 */
  var scoreText: Option[String] = None
  /** 对应的课程成绩 */
  var courseGrade: CourseGrade = _
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

  def this(id: Long, gradeType: GradeType, score: Option[Float], scoreText: Option[String],
           gradingMode: GradingMode, passed: Boolean, status: Int) = {
    this()
    this.id = id
    this.gradeType = gradeType
    this.score = score
    this.scoreText = scoreText
    this.gradingMode = gradingMode
    this.passed = passed
    this.status = status
  }

}
