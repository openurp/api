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
import org.openurp.base.edu.model.Course
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

  def getCourseResult(course: Course): Option[AuditCourseResult] = {
    if null == groupResults then None
    else
      val gIter = groupResults.iterator
      var result: Option[AuditCourseResult] = None
      while (gIter.hasNext && result.isEmpty) {
        val g = gIter.next()
        result = g.courseResults.find(_.course == course)
      }
      result
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
    val inplanTops = tops.filter(_.name != "计划外")
    this.passedCredits = inplanTops.flatMap(_.passedCourses).map(x => x.getCredits(std.level)).sum
    this.passed = inplanTops.count(_.passed) == inplanTops.size && this.owedCredits <= 0.00000001f
    this.predicted = this.owedCredits2 <= 0.00000001f
  }

  def reduceRequired(credits: Float): Unit = {
    this.requiredCredits = Math.max(this.requiredCredits - credits, 0)
  }

  def failedGroups(category: Int = 1): Seq[AuditGroupResult] = {
    val failed = Collections.newBuffer[AuditGroupResult]
    topGroupResults foreach { g =>
      collectFailed(category, g, failed)
    }
    failed.sortBy(_.indexno).toSeq
  }

  private def collectFailed(category: Int, g: AuditGroupResult, failed: mutable.Buffer[AuditGroupResult]): Unit = {
    val passed = category match
      case 1 => g.passed
      case 2 => g.predicted
      case 3 => g.owedCredits3 <= 0
      case _ => false

    if (!passed) {
      if (g.children.isEmpty) failed.addOne(g)
      else {
        val owed = category match
          case 1 => g.owedCredits - g.children.map(_.owedCredits).sum
          case 2 => g.owedCredits2 - g.children.map(_.owedCredits2).sum
          case 3 => g.owedCredits3 - g.children.map(_.owedCredits3).sum
          case _ => 1

        if (Math.abs(owed) < 0.01) {
          for (c <- g.children) collectFailed(category, c, failed)
        } else {
          failed.addOne(g)
        }
      }
    }
  }
}
