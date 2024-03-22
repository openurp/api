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
import org.beangle.data.model.pojo.{Hierarchical, Named, Remark}
import org.openurp.base.edu.model.Course
import org.openurp.code.edu.model.CourseType
import org.openurp.edu.grade.domain.GroupResultAdapter
import org.openurp.edu.program.model.CourseGroup

import scala.annotation.tailrec
import scala.collection.mutable.Buffer

object AuditGroupResult {

  def checkPassed(groupResult: AuditGroupResult, isRecursive: Boolean): Unit = {
    if (null == groupResult) {
      return
    }
    var childrenPassed = true
    var childrenPredicted = true
    if (groupResult.children.nonEmpty) {
      val requiredNum = if (groupResult.subCount >= 0) groupResult.subCount else groupResult.children.size.toShort
      var passed = 0
      var predictedPassed = 0
      for (childResult <- groupResult.children) {
        if childResult.passed then passed += 1
        if childResult.predicted then predictedPassed += 1
      }
      childrenPassed = passed >= requiredNum
      childrenPredicted = predictedPassed >= requiredNum
    }
    groupResult.passed = childrenPassed && groupResult.auditStat.passed
    groupResult.predicted = childrenPredicted && groupResult.auditStat.predicted
    if (isRecursive) {
      groupResult.parent match {
        case None => checkPassed(new GroupResultAdapter(groupResult.planResult), false)
        case Some(p) => checkPassed(p, true)
      }
    }
  }
}

/** 课程组审核结果
 */
class AuditGroupResult extends LongId, Named, Hierarchical[AuditGroupResult], Remark {

  /** 学分统计 */
  var auditStat = new AuditStat

  /** 课程审核结果 */
  var courseResults: Buffer[AuditCourseResult] = new collection.mutable.ListBuffer[AuditCourseResult]

  /** 课程类型 */
  var courseType: CourseType = _

  /** 是否通过 */
  var passed: Boolean = _

  /** 预计是否通过 */
  var predicted: Boolean = _

  /** 子组数量 */
  var subCount: Short = _

  /** 计划审核结果 */
  var planResult: AuditPlanResult = _

  def attachTo(pl: AuditPlanResult): Unit = {
    planResult = pl
    planResult.groupResults += this
    for (groupResult <- children) {
      groupResult.attachTo(planResult)
    }
  }

  def detach(): Unit = {
    if (null != planResult) planResult.groupResults -= this
    planResult = null
    for (groupResult <- children) groupResult.detach()
  }

  def this(group: CourseGroup) = {
    this()
    this.name = group.name
    this.courseType = group.courseType
  }

  def SuperResult: AuditGroupResult = {
    if ((null != planResult)) new GroupResultAdapter(planResult) else null
  }

  def addCourseResult(cr: AuditCourseResult): Unit = {
    cr.groupResult = this
    courseResults += cr
    if cr.passed then addPassedCourse(this, cr.course)
    else if cr.taking then addTakingCourse(this, cr.course)
  }

  def updateCourseResult(rs: AuditCourseResult): Unit = {
    if (rs.passed) addPassedCourse(rs.groupResult, rs.course)
  }

  @tailrec
  private def addPassedCourse(groupResult: AuditGroupResult, course: Course): Unit = {
    if null != groupResult then
      groupResult.auditStat.addPassed(course, groupResult.planResult.std.level)
      groupResult.parent match {
        case None =>
          groupResult.planResult.auditStat.addPassed(course, groupResult.planResult.std.level)
        case Some(p) => addPassedCourse(p, course)
      }
  }

  @tailrec
  private def addTakingCourse(groupResult: AuditGroupResult, course: Course): Unit = {
    if null != groupResult then
      groupResult.auditStat.addTaking(course, groupResult.planResult.std.level)
      groupResult.parent match {
        case None =>
          groupResult.planResult.auditStat.addTaking(course, groupResult.planResult.std.level)
        case Some(p) => addTakingCourse(p, course)
      }
  }

  def addChild(gr: AuditGroupResult): Unit = {
    gr.parent = Some(this)
    this.children += gr
  }

  def removeChild(gr: AuditGroupResult): Unit = {
    gr.parent = null
    this.children -= gr
  }

  def checkPassed(isRecursive: Boolean): Unit = {
    AuditGroupResult.checkPassed(this, isRecursive)
  }
}
