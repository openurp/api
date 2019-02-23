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
package org.openurp.edu.graduation.plan.domain

import org.openurp.edu.base.model.Course
import org.openurp.edu.graduation.plan.model.GroupAuditResult
import org.openurp.edu.program.plan.model.CourseGroup
import org.openurp.edu.program.plan.model.PlanCourse
import org.openurp.edu.program.plan.domain.PlanUtils
import org.beangle.commons.collection.Collections

object DefaultGroupResultBuilder extends GroupResultBuilder {

  def buildResult(context: PlanAuditContext, group: CourseGroup): GroupAuditResult = {
    val result = new GroupAuditResult()
    var creditsRequired = group.credits
    if (context.setting.auditTerms != null && context.setting.auditTerms.length != 0) {
      creditsRequired = 0f
      var groupCourseCredits = 0f
      var creditsNeedCompare = false
      val auditedCourses = Collections.newSet[Course]
      for (i <- 0 until context.setting.auditTerms.length) {
        val term = context.setting.auditTerms(i)
        creditsRequired += PlanUtils.getGroupCredits(group, term)
        if (group.children.isEmpty && !group.planCourses.isEmpty &&
          group.compulsory) {
          creditsNeedCompare = true
          for (planCourse <- group.planCourses if !auditedCourses.contains(planCourse.course) && planCourse.terms.contains(term)) {
            groupCourseCredits += planCourse.course.credits
            auditedCourses.add(planCourse.course)
          }
        }
      }
      if (creditsNeedCompare) {
        creditsRequired = if (java.lang.Float.compare(creditsRequired, groupCourseCredits) <
          0) creditsRequired else groupCourseCredits
      }
    }
    result.auditStat.requiredCredits = creditsRequired
    if (context.setting.partial) {
      result.auditStat.requiredCount = 0
    } else {
      result.auditStat.requiredCount = group.courseCount
    }
    result.courseType = group.courseType
    result.name = group.name
    result.subCount = group.subCount
    result.indexno = group.indexno
    result.planResult = context.result
    result
  }
}
