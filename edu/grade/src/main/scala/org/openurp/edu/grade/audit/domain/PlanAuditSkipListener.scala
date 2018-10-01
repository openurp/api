/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.audit.domain

import org.openurp.edu.base.code.model.CourseType
import org.openurp.edu.base.model.Course
import org.openurp.edu.grade.audit.model.GroupAuditResult
import org.openurp.edu.grade.course.model.CourseGrade
import org.openurp.edu.program.plan.model.CourseGroup
import org.openurp.edu.program.plan.model.CoursePlan
import org.openurp.edu.program.plan.model.PlanCourse
import org.openurp.edu.program.plan.domain.PlanUtils
import org.beangle.commons.collection.Collections

/**
 * 跳过审核Listener，可以判断计划是否跳过审核，课程组是否跳过审核，计划课程是否跳过审核<br>
 * 目前只支持课程组是否跳过审核
 */
class PlanAuditSkipListener extends PlanAuditListener {

  def startPlanAudit(context: PlanAuditContext): Boolean = true

  def startCourseAudit(context: PlanAuditContext, groupResult: GroupAuditResult, planCourse: PlanCourse): Boolean = {
    val standard = context.setting
    if (null == standard) {
      return true
    }
    val auditTerms = context.auditTerms
    // 没有指定具体审核哪个学期，那么就审核全部学期
    if (auditTerms == null || auditTerms.length == 0) {
      return true
    }
    if (standard.isDisaudit(planCourse.group.courseType)) {
      return false
    }
    // 开课学期在审核学期里
    for (j <- 0 until auditTerms.length if planCourse.terms.contains(auditTerms(j))) {
      return true
    }
    // TODO 如果不审核，那么就应该将这个课程从审核结果中拿掉
    false
  }

  def startGroupAudit(context: PlanAuditContext, courseGroup: CourseGroup, groupResult: GroupAuditResult): Boolean = {
    val standard = context.setting
    if (null == standard) {
      return true
    }
    if (standard.isDisaudit(courseGroup.courseType)) {
      for (grade <- context.stdGrade.grades if standard.disauditCourseTypes.contains(grade.courseType)) {
        context.stdGrade.useGrades(grade.course)
      }
      val oriCreditsRequired = context.result.auditStat.creditsRequired
      val oriNumRequired = context.result.auditStat.numRequired
      context.result.auditStat.creditsRequired = (oriCreditsRequired - courseGroup.credits)
      context.result.auditStat.numRequired = (oriNumRequired - courseGroup.courseNum)
      return false
    }
    true
  }

  def endPlanAudit(context: PlanAuditContext) {
  }

  private def getPlanCourse(group: CourseGroup, course: Course): PlanCourse = {
    group.planCourses.find(_.course == course).getOrElse(null)
  }

  private def extractDisauditCourses(plan: CoursePlan, disauditCourseTypes: Set[CourseType]): collection.Set[Course] = {
    val res = Collections.newSet[Course]
    plan.groups foreach { group =>
      if (disauditCourseTypes.contains(group.courseType)) {
        res ++= (extractDescendCourses(group))
      }
    }
    res
  }

  private def extractDescendCourses(group: CourseGroup): collection.Set[Course] = {
    val res = Collections.newSet[Course]
    for (pcourse <- group.planCourses) {
      res.add(pcourse.course)
    }
    for (child <- group.children) {
      res ++= extractDescendCourses(child)
    }
    res
  }
}
