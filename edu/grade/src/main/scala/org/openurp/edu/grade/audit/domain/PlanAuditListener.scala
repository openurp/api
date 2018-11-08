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
package org.openurp.edu.grade.audit.domain

import org.openurp.edu.grade.audit.model.GroupAuditResult
import org.openurp.edu.program.plan.model.CourseGroup
import org.openurp.edu.program.plan.model.PlanCourse

trait PlanAuditListener {

    /**
   * 开始审核计划
   *
   * @return false 表示不能继续审核
   */
  def startPlanAudit(context: PlanAuditContext): Boolean

  /**
   *  开始审核课程组
   *  @return false 表示不能继续审核
   */
  def startGroupAudit(context: PlanAuditContext, courseGroup: CourseGroup, groupResult: GroupAuditResult): Boolean

  /**
   *  开始审核课程
   * @return false 表示不能继续审核
   */
  def startCourseAudit(context: PlanAuditContext, groupResult: GroupAuditResult, planCourse: PlanCourse): Boolean

  /**
   * 结束审核计划
   */
  def endPlanAudit(context: PlanAuditContext): Unit
}
