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

package org.openurp.edu.grade.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.CourseType

import scala.collection.mutable

class AuditPlanResult extends LongId with Updated with Remark {

  var std: Student = _

  var auditStat = new AuditStat

  var groupResults: mutable.Buffer[AuditGroupResult] = Collections.newBuffer[AuditGroupResult]

  /** 是否通过 */
  var passed: Boolean = _

  /** 预计是否通过 */
  var predicted: Boolean = _

  /** 和上次比较的更新内容 */
  var updates: Option[String] = None

  var archived: Boolean = false

  def topGroupResults: collection.Seq[AuditGroupResult] = {
    val results = new collection.mutable.ListBuffer[AuditGroupResult]
    for (result <- groupResults if result.parent.isEmpty) {
      results += result
    }
    results
  }

  def addGroupResult(rs: AuditGroupResult): Unit = {
    rs.planResult = this
    this.groupResults += rs
  }

  def removeGroupResult(rs: AuditGroupResult): Unit = {
    rs.planResult = null
    this.groupResults -= rs
  }

  def getGroupResult(typ: CourseType): Option[AuditGroupResult] = {
    if null == groupResults then None
    else
      var rs: Option[AuditGroupResult] = None
      for (groupAuditResult <- groupResults; if rs.isEmpty) {
        rs = findGroupResult(groupAuditResult, typ)
      }
      rs
  }

  private def findGroupResult(groupResult: AuditGroupResult, typ: CourseType): Option[AuditGroupResult] = {
    if (typ == groupResult.courseType) {
      return Some(groupResult)
    }
    var rs: Option[AuditGroupResult] = None
    for (childResult <- groupResult.children; if rs.isEmpty) {
      rs = findGroupResult(childResult, typ)
    }
    rs
  }

  def this(student: Student) = {
    this()
    std = student
  }

}
