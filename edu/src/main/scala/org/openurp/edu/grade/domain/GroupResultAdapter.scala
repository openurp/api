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

import org.openurp.edu.grade.model.{AuditCourseResult, AuditGroupResult, AuditPlanResult}

class GroupResultAdapter(var result: AuditPlanResult) extends AuditGroupResult {

  subCount = -1
  name = "计划"
  planResult = result
  passed = result.passed

  override def removeChild(gr: AuditGroupResult): Unit = {
  }

  override def addChild(gr: AuditGroupResult): Unit = {
    //    this.auditResult = result.auditResult
  }

  override def attachTo(planResult: AuditPlanResult): Unit = {
  }

  override def detach(): Unit = {
  }

  override def addCourseResult(cr: AuditCourseResult): Unit = {
  }

  override def updateCourseResult(rs: AuditCourseResult): Unit = {
  }

  def checkPassed(): Unit = {
    AuditGroupResult.checkPassed(this, false)
  }

  override def checkPassed(isRecursive: Boolean): Unit = {
    AuditGroupResult.checkPassed(this, isRecursive)
  }

}
