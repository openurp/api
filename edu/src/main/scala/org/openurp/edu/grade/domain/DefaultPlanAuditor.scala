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

package org.openurp.edu.grade.domain

import org.openurp.edu.grade.model.{AuditStat, CourseAuditResult, GroupAuditResult, PlanAuditResult}
import org.openurp.edu.program.model.CourseGroup

import java.time.Instant

class DefaultPlanAuditor extends PlanAuditor {

  def audit(context: PlanAuditContext): PlanAuditResult = {
    val planAuditResult = new PlanAuditResult(context.std)
    planAuditResult.passed = false
    planAuditResult.remark = null
    planAuditResult.updatedAt = Instant.now
    planAuditResult.auditStat = new AuditStat()
    context.result = planAuditResult
    val plan = context.coursePlan
    if (null == plan || null == context.stdGrade) {
      return context.result
    }

    if (context.listeners.exists(!_.start(context))) {
      return planAuditResult
    }

    val courseGroupAdapter = new CourseGroupAdapter(context.coursePlan)
    val groupResultAdapter = new GroupResultAdapter(planAuditResult)
    val creditsRequired = context.coursePlan.credits
    planAuditResult.auditStat.requiredCredits = creditsRequired
    var numRequired = 0
    for (group <- context.coursePlan.groups if group.parent == null) {
      numRequired += group.courseCount
    }
    planAuditResult.auditStat.requiredCount = numRequired
    auditGroup(context, courseGroupAdapter, groupResultAdapter)
    for (listener <- context.listeners) {
      listener.end(context)
    }
    context.coursePlan.program.offsetType foreach { offsetType =>
      context.result.getGroupResult(offsetType) foreach { lastTarget =>
        if (lastTarget.auditStat.passedCredits == 0 && lastTarget.auditStat.requiredCredits == 0 &&
          lastTarget.courseResults.isEmpty) {
          context.result.removeGroupResult(lastTarget)
        }
      }
    }
    planAuditResult
  }

  private def auditGroup(context: PlanAuditContext, courseGroup: CourseGroup, groupAuditResult: GroupAuditResult): Unit = {
    val planAuditResult = context.result
    courseGroup.children foreach { child =>
      val childResult = DefaultGroupResultBuilder.buildResult(context, child)
      groupAuditResult.addChild(childResult)
      planAuditResult.addGroupResult(childResult)
      if (context.listeners.exists(!_.startGroup(context, child, childResult))) {
        planAuditResult.auditStat.reduceRequired(childResult.auditStat.requiredCredits, childResult.auditStat.requiredCount)
        groupAuditResult.removeChild(childResult)
        planAuditResult.removeGroupResult(childResult)
      } else {
        auditGroup(context, child, childResult)
      }
    }

    courseGroup.planCourses foreach { planCourse =>
      val planCourseAuditResult = new CourseAuditResult(planCourse)
      val courseGrades = context.stdGrade.useGrades(planCourse.course)
      if (courseGrades.nonEmpty || planCourse.compulsory) {
        planCourseAuditResult.checkPassed(courseGrades)
        groupAuditResult.addCourseResult(planCourseAuditResult)
      }
    }
    groupAuditResult.checkPassed(false)
  }

}
