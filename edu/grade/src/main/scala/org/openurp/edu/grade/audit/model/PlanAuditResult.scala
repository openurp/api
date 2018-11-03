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
package org.openurp.edu.grade.audit.model

import scala.collection.mutable.Buffer

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.edu.base.code.model.CourseType
import org.openurp.edu.base.model.Student

class PlanAuditResult extends LongId with Updated {

  var std: Student = _

  var auditStat = new AuditStat

  var groupResults: Buffer[GroupAuditResult] = new collection.mutable.ListBuffer[GroupAuditResult]

  var passed: Boolean = _

  var gpa: Float = _

  /**平均分*/
  var ga: Float = _

  var remark: String = _

  var departOpinion: String = _

  var finalOpinion: String = _

  var updates: String = _

  var published: Boolean = false

  var auditor: String = _

  var partial: Boolean = _

  def topGroupResults: Seq[GroupAuditResult] = {
    val results = new collection.mutable.ListBuffer[GroupAuditResult]
    for (result <- groupResults if null == result.parent) {
      results += result
    }
    results
  }

  def addGroupResult(rs: GroupAuditResult) {
    rs.planResult = this
    this.groupResults += rs
  }

  def removeGroupResult(rs: GroupAuditResult) {
    rs.planResult = null
    this.groupResults -= rs
  }

  def getGroupResult(`type`: CourseType): GroupAuditResult = {
    if (null == groupResults) {
      return null
    }
    for (groupAuditResult <- groupResults) {
      val res = findGroupResult(groupAuditResult, `type`)
      if (null != res) {
        return res
      }
    }
    null
  }

  private def findGroupResult(groupResult: GroupAuditResult, `type`: CourseType): GroupAuditResult = {
    if (`type` == groupResult.courseType) {
      return groupResult
    }
    for (childResult <- groupResult.children) {
      val res = findGroupResult(childResult, `type`)
      if (null != res) return res
    }
    null
  }

  def this(student: Student) {
    this
    std = student
  }

}
