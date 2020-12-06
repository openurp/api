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
package org.openurp.edu.grade.plan.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.{Course, Terms}
import org.openurp.edu.grade.course.model.CourseGrade
import org.openurp.edu.program.model.PlanCourse

class CourseAuditResult extends LongId with Remark {

  var groupResult: GroupAuditResult = _

  var course: Course = _

  var scores: String = _

  var passed: Boolean = _

  var terms: Terms = Terms.empty

  var compulsory: Boolean = _

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
    this
    this.course = planCourse.course
    this.compulsory = planCourse.compulsory
  }

}
