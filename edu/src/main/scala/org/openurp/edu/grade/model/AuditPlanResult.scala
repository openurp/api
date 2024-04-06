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

import scala.collection.mutable

class AuditPlanResult extends LongId with Updated with Remark {

  var std: Student = _

  var groupResults: mutable.Buffer[AuditGroupResult] = Collections.newBuffer[AuditGroupResult]

  /** 是否通过 */
  var passed: Boolean = _

  /** 预计是否通过 */
  var predicted: Boolean = _

  /** 要求学分 */
  var requiredCredits: Float = _

  /** 通过学分 */
  var passedCredits: Float = _

  /** 欠学分 */
  var owedCredits: Float = _

  /** 预计通过后所欠学分 */
  var owedCredits2: Float = _

  /** 在读通过后所欠学分 */
  var owedCredits3: Float = _

  /** 和上次比较的更新内容 */
  var updates: Option[String] = None

  var archived: Boolean = false

  @transient private var groupCache: Map[String, AuditGroupResult] = _

  def buildGroupCache(): Unit = {
    groupCache = groupResults.map(x => x.name -> x).toMap
  }

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

  def getGroupResult(name: String): Option[AuditGroupResult] = {
    if null == groupResults then None
    else if (null == groupCache) groupResults.find(_.name == name)
    else groupCache.get(name)
  }

  def this(student: Student) = {
    this()
    std = student
  }

  /** 计算子节点和自身的学分以及完成状态
   */
  def stat(cascade: Boolean = true): Unit = {
    val tops = topGroupResults
    //每个子节点也进行统计
    if (cascade) tops.foreach(_.stat())
    this.owedCredits = tops.map(_.owedCredits).sum
    this.owedCredits2 = tops.map(_.owedCredits2).sum
    this.owedCredits3 = tops.map(_.owedCredits3).sum
    this.passed = tops.count(_.passed) == tops.size && this.owedCredits <= 0.00000001f
    this.predicted = this.owedCredits2 <= 0.00000001f
  }

  def reduceRequired(credits: Float): Unit = {
    this.requiredCredits = Math.max(this.requiredCredits - credits, 0)
  }
}
