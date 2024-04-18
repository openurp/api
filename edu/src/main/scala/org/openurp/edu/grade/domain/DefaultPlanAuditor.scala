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

import org.beangle.commons.collection.Collections
import org.openurp.edu.grade.model.*
import org.openurp.edu.program.model.CourseGroup

import java.time.Instant

class DefaultPlanAuditor extends PlanAuditor {

  def audit(context: AuditPlanContext): AuditPlanResult = {
    val apr = new AuditPlanResult(context.std)
    apr.passed = false
    apr.remark = null
    apr.updatedAt = Instant.now
    context.result = apr
    val plan = context.coursePlan
    if (null == plan || null == context.stdGrade) {
      return context.result
    }

    val creditsRequired = context.coursePlan.credits
    apr.requiredCredits = creditsRequired
    plan.topGroups foreach { g =>
      val topResult = DefaultAuditGroupResultBuilder.buildResult(context, g)
      apr.addGroupResult(topResult)
      initResult(context, g, topResult)
    }
    apr.buildGroupCache() //所有监听启动之前，注册所有的组
    context.listeners.foreach(_.start(context))
    plan.topGroups foreach (g => auditGroup(context, g, apr.getGroupResult(g.name).get))
    context.listeners.foreach(_.end(context))
    apr.stat()
    context.coursePlan.program.offsetType foreach { offsetType =>
      apr.getGroupResult(offsetType.name) foreach { lastTarget =>
        processConvertCredits(lastTarget, apr)
        if lastTarget.passedCredits == 0 && lastTarget.requiredCredits == 0 && lastTarget.courseResults.isEmpty then
          apr.removeGroupResult(lastTarget)
      }
    }
    cleanupElectiveCourses(apr)
    apr
  }

  private def initResult(context: AuditPlanContext, courseGroup: CourseGroup, gr: AuditGroupResult): Unit = {
    val result = context.result
    courseGroup.children foreach { child =>
      val childResult = DefaultAuditGroupResultBuilder.buildResult(context, child)
      gr.addChild(childResult)
      result.addGroupResult(childResult)
      initResult(context, child, childResult)
    }
  }

  private def auditGroup(context: AuditPlanContext, courseGroup: CourseGroup, gr: AuditGroupResult): Unit = {
    val result = context.result
    //先组内课程，然后再审核审核子组
    courseGroup.planCourses foreach { pc =>
      val cr = new AuditCourseResult(pc)
      val courseGrades = context.stdGrade.useGrade(pc.course)
      //不要过滤计划内的课程，对于选修组内的选修课，无成绩的情况下，事后在删除
      cr.updatePassed(courseGrades)
      gr.addCourseResult(cr)
    }

    courseGroup.children foreach { child =>
      val childResult = context.result.getGroupResult(child.name).get
      if (context.listeners.exists(!_.startGroup(context, child, childResult))) {
        result.reduceRequired(childResult.requiredCredits)
        childResult.reduceRequired(childResult.requiredCredits)
      } else {
        auditGroup(context, child, childResult)
      }
    }
  }

  /** 将其他组多出的学分转移到最后的【公选课】上
   *
   * @param target
   * @param result
   */
  protected def processConvertCredits(target: AuditGroupResult, result: AuditPlanResult): Unit = {
    val parents = Collections.newSet[AuditGroupResult] // 从当前节点查找祖先
    val sibling = Collections.newSet[AuditGroupResult] // 同一级别的兄弟节点
    var start = target.parent.orNull
    while (null != start && !parents.contains(start)) {
      parents.add(start)
      start = start.parent.orNull
    }
    target.parent foreach { p =>
      sibling ++= p.children
      sibling.remove(target)
    }
    var otherConverted = 0f
    var siblingConverted = 0f
    for (gr <- result.groupResults) {
      // 自己和父节点过滤掉
      if (gr != target && !parents.contains(gr)) {
        val rest = gr.passedCredits - gr.requiredCredits
        if (sibling.contains(gr)) {
          siblingConverted += (if rest > 0 then rest else 0f)
        } else if (gr.parent.isEmpty) {
          otherConverted += (if rest > 0 then rest else 0f)
        }
      }
    }
    target.convertedCredits = otherConverted + siblingConverted
    for (r <- parents) r.convertedCredits = otherConverted
    target.stat()
    result.stat(false)
  }

  /** 清除空的不及格的选修课
   *
   * @param result
   */
  def cleanupElectiveCourses(result: AuditPlanResult): Unit = {
    for (gr <- result.groupResults; if gr.courseType.optional || gr.passed) {
      val empties = gr.courseResults filter (x => !x.compulsory && !x.passed && !x.predicted && !x.taking && !x.hasGrade)
      gr.courseResults.subtractAll(empties)
    }
  }
}
