/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.course.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.edu.base.code.model.{ GradeType, ScoreMarkStyle }
import org.openurp.edu.grade.model.Grade

/**
 * 总评成绩
 * </p>
 * 期末总评成绩,补考总评成绩
 *
 *
 * @author chaostone
 * @since 2005
 */
class GaGrade extends LongId with Grade with Updated {
  /** 成绩类型 */
  var gradeType: GradeType = _
  /** 成绩记录方式 */
  var markStyle: ScoreMarkStyle = _
  /** 得分 */
  var score: Option[Float] = None
  /** 得分字面值 */
  var scoreText: String = _
  /** 对应的课程成绩 */
  var courseGrade: CourseGrade = _
  /** 成绩状态 */
  var status: Int = _
  /** 是否通过 */
  var passed: Boolean = _
  /** 操作者 */
  var operator: String = _
  /**绩点*/
  var gp: Option[Float] = None
  /**加减修正分*/
  var delta: Option[Float] = None

  def std = courseGrade.std
  // 大的成绩放前面
  override def compare(grade: Grade): Int = {
    if (None == score) return 1
    else if (None == grade.score) return -1
    return grade.score.get.compareTo(score.get)
  }

  def this(id: Long, gradeType: GradeType, score: Option[Float], scoreText: String, markStyle: ScoreMarkStyle, passed: Boolean, status: Int) {
    this()
    this.id = id
    this.gradeType = gradeType
    this.score = score
    this.scoreText = scoreText
    this.markStyle = markStyle
    this.passed = passed
    this.status = status
  }

}