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

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.{Course, Terms}
import org.openurp.edu.program.model.PlanCourse

/** 课程审核结果
 */
class AuditCourseResult extends LongId with Remark {

  /** 课程组审核结果 */
  var groupResult: AuditGroupResult = _

  /** 课程 */
  var course: Course = _

  /** 成绩 */
  var scores: String = _

  /** 是否通过 */
  var passed: Boolean = _

  /** 计划就读学期 */
  var terms: Terms = Terms.empty

  /** 是否必修 */
  var compulsory: Boolean = _

  /** 是否在读 */
  var taking: Boolean = _

  def checkPassed(grades: collection.Seq[CourseGrade]): Unit = {
    val sb = new StringBuilder
    if (Collections.isEmpty(grades)) {
      scores = "--"
    } else {
      for (grade <- grades) {
        sb.append(grade.scoreText.getOrElse("--")).append(" ")
        if (!passed) passed = grade.passed
      }
      scores = sb.toString
    }
  }

  def checkPassed(grades: collection.Seq[CourseGrade], substituteGrades: collection.Seq[CourseGrade]): Unit = {
    checkPassed(grades)
    if (!passed && substituteGrades.nonEmpty) {
      passed = substituteGrades.head.passed
    }
  }

  def this(planCourse: PlanCourse) = {
    this()
    this.course = planCourse.course
    this.compulsory = planCourse.compulsory
  }

}
