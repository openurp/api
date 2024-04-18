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
import org.beangle.data.model.pojo.{Hierarchical, Named, Remark}
import org.openurp.base.edu.model.Course
import org.openurp.code.edu.model.CourseType
import org.openurp.edu.program.model.CourseGroup

import scala.collection.mutable.Buffer

/** 课程组审核结果
 */
class AuditGroupResult extends LongId, Named, Hierarchical[AuditGroupResult], Remark {

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

  /** 转换学分 */
  var convertedCredits: Float = _

  @transient var passedCourses = Collections.newSet[Course]
  @transient var predictedCourses = Collections.newSet[Course]
  @transient var takingCourses = Collections.newSet[Course]

  /** 课程审核结果 */
  var courseResults: Buffer[AuditCourseResult] = new collection.mutable.ListBuffer[AuditCourseResult]

  /** 课程类型 */
  var courseType: CourseType = _

  /** 是否通过 */
  var passed: Boolean = _

  /** 子组数量 */
  var subCount: Short = _

  /** 计划审核结果 */
  var planResult: AuditPlanResult = _

  def attachTo(pl: AuditPlanResult): Unit = {
    planResult = pl
    planResult.groupResults += this
    for (groupResult <- children) {
      groupResult.attachTo(planResult)
    }
  }

  def detach(): Unit = {
    if (null != planResult) planResult.groupResults -= this
    planResult = null
    for (groupResult <- children) groupResult.detach()
  }

  def this(name: String, courseType: CourseType) = {
    this()
    this.name = name
    this.courseType = courseType
    this.subCount = 0.toShort
  }

  def this(group: CourseGroup) = {
    this(group.name, group.courseType)
    this.subCount = group.subCount
  }

  def addCourseResult(cr: AuditCourseResult): Unit = {
    cr.groupResult = this
    if !courseResults.exists(_.course == cr.course) then courseResults += cr
    //may passed and taking are both true.
    if cr.passed then addCourse(this, cr.course, AuditCourseLevel.Passed)
    else if cr.predicted then addCourse(this, cr.course, AuditCourseLevel.Predicted)
    else if cr.taking then addCourse(this, cr.course, AuditCourseLevel.Taking)
  }

  def getCourseResult(course: Course): Option[AuditCourseResult] = {
    courseResults.find(_.course == course)
  }

  private def addCourse(gr: AuditGroupResult, course: Course, level: AuditCourseLevel): Unit = {
    val eduLevel = gr.planResult.std.level
    val credits = course.getCredits(eduLevel)
    level match
      case AuditCourseLevel.Passed => gr.passedCourses.add(course)
      case AuditCourseLevel.Predicted => gr.predictedCourses.add(course)
      case AuditCourseLevel.Taking => gr.takingCourses.add(course)
    //防止多个子组有相同课程，通过学分重复累计
    gr.parent foreach { p => addCourse(p, course, level) }
  }

  def addChild(gr: AuditGroupResult): Unit = {
    gr.parent = Some(this)
    this.children += gr
  }

  def removeChild(gr: AuditGroupResult): Unit = {
    gr.parent = null
    this.children -= gr
  }

  /** 计算子节点和自身的学分以及完成状态
   */
  def stat(): Unit = {
    var sonPassed = true
    var sonOwedCredits: Float = 0f
    var sonOwedCredits2: Float = 0f
    var sonOwedCredits3: Float = 0f
    var childOwedList = Collections.newBuffer[AuditGroupResult]
    var childOwedList2 = Collections.newBuffer[AuditGroupResult]
    var childOwedList3 = Collections.newBuffer[AuditGroupResult]
    if (this.children.nonEmpty) {
      //每个子节点也进行统计
      this.children.foreach(_.stat())
      val requiredNum = if (this.subCount >= 0) this.subCount else this.children.size.toShort
      var passedCnt = 0
      for (childResult <- this.children) {
        if childResult.passed then passedCnt += 1
        //不管是否完成，都要登记，以备按照欠分排序
        if (requiredNum > 0 && childResult.requiredCredits > 0) { //忽略没有完成的组
          childOwedList.addOne(childResult)
          childOwedList2.addOne(childResult)
          childOwedList3.addOne(childResult)
        }
      }
      //取欠分最小的前requiredNum个组
      childOwedList = childOwedList.sortBy(_.owedCredits).take(requiredNum)
      childOwedList2 = childOwedList2.sortBy(_.owedCredits2).take(requiredNum)
      childOwedList3 = childOwedList3.sortBy(_.owedCredits3).take(requiredNum)
      sonPassed = passedCnt >= requiredNum

      sonOwedCredits = childOwedList.map(_.owedCredits).sum
      sonOwedCredits2 = childOwedList2.map(_.owedCredits2).sum
      sonOwedCredits3 = childOwedList3.map(_.owedCredits3).sum
    }

    //统计完成学分
    val eduLevel = this.planResult.std.level
    this.passedCredits = passedCourses.toSeq.map(_.getCredits(eduLevel)).sum //must toseq
    val passedCredits2 = predictedCourses.toSeq.map(_.getCredits(eduLevel)).sum
    val passedCredits3 = takingCourses.toSeq.map(_.getCredits(eduLevel)).sum

    //计算必修部分(compulsory part)的欠分、欠分2
    val cp = courseResults.filter(_.compulsory)
    val cpOwedCredits = cp.filter(!_.passed).map(_.course.getCredits(eduLevel)).sum
    val cpOwedCredits2 = cp.filter(x => !x.passed && !x.predicted).map(_.course.getCredits(eduLevel)).sum
    val cpOwedCredits3 = cp.filter(x => !x.passed && !x.predicted && !x.taking).map(_.course.getCredits(eduLevel)).sum

    //计算剩余的欠分
    val totalOwed = requiredCredits - convertedCredits - (cpOwedCredits + sonOwedCredits + passedCredits)
    val totalOwed2 = requiredCredits - convertedCredits - (cpOwedCredits2 + sonOwedCredits2 + passedCredits + passedCredits2)
    val totalOwed3 = requiredCredits - convertedCredits - (cpOwedCredits3 + sonOwedCredits3 + passedCredits + passedCredits2 + passedCredits3)

    this.owedCredits = cpOwedCredits + sonOwedCredits + (if (totalOwed < 0) 0 else totalOwed)
    this.owedCredits2 = cpOwedCredits2 + sonOwedCredits2 + (if (totalOwed2 < 0) 0 else totalOwed2)
    this.owedCredits3 = cpOwedCredits3 + sonOwedCredits3 + (if (totalOwed3 < 0) 0 else totalOwed3)
    this.passed = sonPassed && this.owedCredits <= 0
  }

  /** 未完成的组
   *
   * @return
   */
  def neededGroups: Int = {
    subCount - children.count(_.passed)
  }

  def predicted: Boolean = owedCredits2 <= 0

  def reduceRequired(credits: Float): Unit = {
    this.requiredCredits = Math.max(this.requiredCredits - credits, 0)
    if (parent.nonEmpty) {
      parent.get.reduceRequired(credits)
    }
  }
}
