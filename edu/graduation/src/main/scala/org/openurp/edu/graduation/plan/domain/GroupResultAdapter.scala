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

import org.openurp.edu.graduation.plan.model.{CourseAuditResult, GroupAuditResult, PlanAuditResult}

class GroupResultAdapter(var result: PlanAuditResult) extends GroupAuditResult {

  subCount = -1
  name = "计划"
  planResult = result
  passed = result.passed

  override def removeChild(gr: GroupAuditResult): Unit = {
  }

  override def addChild(gr: GroupAuditResult): Unit = {
    //    this.auditResult = result.auditResult
  }

  override def attachTo(planResult: PlanAuditResult): Unit = {
  }

  override def detach(): Unit = {
  }

  override def addCourseResult(courseResult: CourseAuditResult): Unit = {
  }

  override def updateCourseResult(rs: CourseAuditResult): Unit = {
  }

  def checkPassed(): Unit = {
    GroupAuditResult.checkPassed(this, false)
  }

  override def checkPassed(isRecursive: Boolean): Unit = {
    GroupAuditResult.checkPassed(this, isRecursive)
  }

}
