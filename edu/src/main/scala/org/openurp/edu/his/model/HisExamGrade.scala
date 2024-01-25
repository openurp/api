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
import org.openurp.base.model.ArchivedByYear
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{ExamStatus, GradeType, GradingMode}
import org.openurp.edu.grade.model.{ExamGrade, Grade}

/** 归档考试成绩
 */
@archive
class HisExamGrade extends LongId, Grade, ArchivedByYear {
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
  /** 考试情况 */
  var examStatus: ExamStatus = _
  /** 百分比 */
  var scorePercent: Option[Short] = None

  def std: Student = courseGrade.std

  def convert(): ExamGrade = {
    val eg = new ExamGrade()
    eg.gradeType = gradeType
    eg.gradingMode = gradingMode
    eg.score = score
    eg.scoreText = scoreText
    eg.status = status
    eg.passed = passed
    eg.operator = operator
    eg.examStatus = examStatus
    eg.scorePercent = scorePercent
    eg
  }

}
