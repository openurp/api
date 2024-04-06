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
import org.openurp.code.edu.model.CourseTakeType
import org.openurp.edu.program.model.{PlanCourse, SharePlanCourse}

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

  /** 是否预计能通过 */
  var predicted: Boolean = _

  /** 是否修读过，产生过成绩记录 */
  var hasGrade: Boolean = _

  /** 若通过，通过的途径 */
  var passedWay: Option[CoursePassedWay] = None

  def updatePassed(grades: Iterable[CourseGrade]): AuditCourseResult = {
    hasGrade = false
    if (Collections.isEmpty(grades)) {
      scores = "--"
    } else {
      hasGrade = true
      val sb = new StringBuilder
      for (grade <- grades) {
        sb.append(grade.scoreText.getOrElse("--")).append(" ")
        if (!passed) passed = grade.passed
      }
      scores = sb.toString
      if passed then
        val isRepeat = grades.head.courseTakeType.id == CourseTakeType.Repeat
        updatePassedWay(if isRepeat then CoursePassedWay.ByGrade else CoursePassedWay.ByRepeat)
    }
    this
  }

  def updatePassed(grades: Iterable[CourseGrade], substituteGrades: Iterable[CourseGrade]): AuditCourseResult = {
    updatePassed(grades)
    if passed then updatePassedWay(CoursePassedWay.ByGrade)
    if (!passed && substituteGrades.nonEmpty) {
      if substituteGrades.head.passed then
        updatePassedWay(CoursePassedWay.ByAlternative)
        val tempStr = new StringBuffer()
        substituteGrades foreach { grade =>
          tempStr.append(grade.course.name).append('[').append(grade.course.code).append("],")
        }
        if (tempStr.length > 0) tempStr.deleteCharAt(tempStr.length - 1)
        addRemark(tempStr.toString)
    }
    this
  }

  def updatePassedWay(way: CoursePassedWay): Unit = {
    this.passed = true
    this.passedWay = Some(way)
  }

  def this(course: Course) = {
    this()
    this.course = course
    this.scores = "--"
  }

  def this(pc: PlanCourse) = {
    this(pc.course)
    this.terms = pc.terms
    this.compulsory = pc.compulsory
  }

  def this(pc: SharePlanCourse) = {
    this(pc.course)
    this.terms = pc.terms
    this.compulsory = pc.compulsory
  }

  def addRemark(remark: String): Unit = {
    this.remark = this.remark match
      case None => Some(remark)
      case Some(r) => if r.contains(remark) then Some(r) else Some(r + " " + remark)
  }
}
