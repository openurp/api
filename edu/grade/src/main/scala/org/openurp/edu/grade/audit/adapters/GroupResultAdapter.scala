/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2016, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.audit.adapters

import scala.collection.mutable.Buffer

import org.openurp.edu.base.code.model.CourseType
import org.openurp.edu.grade.audit.model.{ AuditStat, CourseAuditResult, GroupAuditResult, PlanAuditResult }

class GroupResultAdapter(var result: PlanAuditResult) extends GroupAuditResult {

  groupNum = -1
  name = "计划"
  planResult = result
  passed = result.passed

  override def removeChild(gr: GroupAuditResult) {
  }

  override def addChild(gr: GroupAuditResult) {
    //    this.auditResult = result.auditResult
  }

  override def attachTo(planResult: PlanAuditResult) {
  }

  override def detach {
  }

  override def addCourseResult(courseResult: CourseAuditResult) {
  }

  override def updateCourseResult(rs: CourseAuditResult) {
  }

  //  override def children: Buffer[GroupAuditResult] = {
  //    val rs = new collection.mutable.ListBuffer[GroupAuditResult]
  //    rs ++= result.topGroupResults
  //    rs
  //  }
  //  override def auditStat_=(auditStat: AuditStat) {
  //    result.auditStat = auditStat
  //  }
  def checkPassed {
    GroupAuditResult.checkPassed(this, false)
  }

  override def checkPassed(isRecursive: Boolean) {
    GroupAuditResult.checkPassed(this, isRecursive)
  }

}
