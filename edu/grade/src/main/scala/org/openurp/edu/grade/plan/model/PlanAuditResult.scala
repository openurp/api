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
package org.openurp.edu.grade.plan.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.edu.base.code.model.CourseType
import org.openurp.edu.base.model.Student

import scala.collection.mutable.Buffer

class PlanAuditResult extends LongId with Updated with Remark {

  var std: Student = _

  var auditStat = new AuditStat

  var groupResults: Buffer[GroupAuditResult] = new collection.mutable.ListBuffer[GroupAuditResult]

  var passed: Boolean = _

  var updates: String = _

  var archived: Boolean = false

  def topGroupResults: collection.Seq[GroupAuditResult] = {
    val results = new collection.mutable.ListBuffer[GroupAuditResult]
    for (result <- groupResults if null == result.parent) {
      results += result
    }
    results
  }

  def addGroupResult(rs: GroupAuditResult): Unit = {
    rs.planResult = this
    this.groupResults += rs
  }

  def removeGroupResult(rs: GroupAuditResult): Unit = {
    rs.planResult = null
    this.groupResults -= rs
  }

  def getGroupResult(typ: CourseType): Option[GroupAuditResult] = {
    if (null == groupResults) {
      return None
    }
    var rs: Option[GroupAuditResult] = None
    for (groupAuditResult <- groupResults; if rs.isEmpty) {
      rs = findGroupResult(groupAuditResult, typ)
    }
    rs
  }

  private def findGroupResult(groupResult: GroupAuditResult, typ: CourseType): Option[GroupAuditResult] = {
    if (typ == groupResult.courseType) {
      return Some(groupResult)
    }
    var rs: Option[GroupAuditResult] = None
    for (childResult <- groupResult.children; if rs.isEmpty) {
      rs = findGroupResult(childResult, typ)
    }
    rs
  }

  def this(student: Student) {
    this
    std = student
  }

}
