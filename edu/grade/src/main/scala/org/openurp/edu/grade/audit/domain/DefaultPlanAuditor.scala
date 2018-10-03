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

import java.time.Instant

import org.openurp.edu.grade.audit.model.AuditStat
import org.openurp.edu.grade.audit.model.CourseAuditResult
import org.openurp.edu.grade.audit.model.GroupAuditResult
import org.openurp.edu.grade.audit.model.PlanAuditResult
import org.openurp.edu.program.plan.domain.PlanUtils
import org.openurp.edu.program.plan.model.CourseGroup

class DefaultPlanAuditor extends PlanAuditor {

  def audit(context: PlanAuditContext): PlanAuditResult = {
    val setting = context.setting
    val planAuditResult = new PlanAuditResult(context.std)
    planAuditResult.passed = false
    planAuditResult.remark = null
    planAuditResult.updatedAt = Instant.now
    planAuditResult.auditStat = new AuditStat()
    planAuditResult.partial = setting.partial
    context.result = planAuditResult
    val plan = context.coursePlan
    if (null == plan || null == context.stdGrade) {
      return context.result
    }

    if (context.listeners.exists(!_.startPlanAudit(context))) {
      return planAuditResult
    }

    val courseGroupAdapter = new CourseGroupAdapter(context.coursePlan)
    val groupResultAdapter = new GroupResultAdapter(planAuditResult)
    var creditsRequired = context.coursePlan.credits
    if (setting.auditTerms != null && setting.auditTerms.length != 0) {
      creditsRequired = 0
      for (i <- 0 until setting.auditTerms.length; group <- context.coursePlan.groups if group.parent == null) {
        creditsRequired += PlanUtils.getGroupCredits(group, setting.auditTerms(i))
      }
    }
    planAuditResult.auditStat.creditsRequired = creditsRequired
    var numRequired = 0
    if (!setting.partial) {
      for (group <- context.coursePlan.groups if group.parent == null) {
        numRequired += group.courseNum
      }
    }
    planAuditResult.auditStat.numRequired = numRequired
    auditGroup(context, courseGroupAdapter, groupResultAdapter)
    for (listener <- context.listeners) {
      listener.endPlanAudit(context)
    }
    val lastTarget = context.result.getGroupResult(setting.convertTarget)
    if (null != lastTarget) {
      if (lastTarget.auditStat.creditsCompleted == 0 && lastTarget.auditStat.creditsRequired == 0 &&
        lastTarget.courseResults.isEmpty) {
        context.result.removeGroupResult(lastTarget)
      }
    }
    planAuditResult
  }

  private def auditGroup(context: PlanAuditContext, courseGroup: CourseGroup, groupAuditResult: GroupAuditResult) {
    val planAuditResult = context.result
    courseGroup.children foreach { child =>
      val childResult = DefaultGroupResultBuilder.buildResult(context, child)
      groupAuditResult.addChild(childResult)
      planAuditResult.addGroupResult(childResult)
      if (context.listeners.exists(!_.startGroupAudit(context, child, childResult))) {
        planAuditResult.auditStat.reduceRequired(
          childResult.auditStat.creditsRequired,
          childResult.auditStat.numRequired)
        groupAuditResult.removeChild(childResult)
        planAuditResult.removeGroupResult(childResult)
      } else {
        auditGroup(context, child, childResult)
      }
    }

    courseGroup.planCourses foreach { planCourse =>
      val skiped = context.listeners.exists(!_.startCourseAudit(context, groupAuditResult, planCourse))
      if (!skiped) {
        val planCourseAuditResult = new CourseAuditResult(planCourse)
        var courseGrades = context.stdGrade.useGrades(planCourse.course)
        if (!courseGrades.isEmpty || planCourse.compulsory) {
          planCourseAuditResult.checkPassed(courseGrades)
          groupAuditResult.addCourseResult(planCourseAuditResult)
        }
      }
    }
    groupAuditResult.checkPassed(false)
  }

}
