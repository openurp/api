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

package org.openurp.edu.program.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.{Numbers, Strings}
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Hierarchical, Remark}
import org.openurp.base.edu.model.Terms
import org.openurp.base.model.CalendarStage
import org.openurp.code.edu.model.{CourseRank, CourseType, TeachingNature}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * 课程设置中的课程组 </p>
 *
 * @author chaostone
 * @since 2009
 */
abstract class AbstractCourseGroup extends LongId, CourseGroup, Cloneable, Hierarchical[CourseGroup], Remark {
  /** 计划 */
  var plan: CoursePlan = _
  /** 计划课程列表 */
  var planCourses: mutable.Buffer[PlanCourse] = new ListBuffer[PlanCourse]
  /** 自定义别名 */
  var givenName: Option[String] = None
  /** 课程类别 */
  var courseType: CourseType = _
  /** 要求学分 */
  var credits: Float = _
  /** 课时 */
  var creditHours: Int = _
  /** 周数 */
  var weeks: Option[Int] = None
  /** 课时比例 */
  var hourRatios: Option[String] = None
  /** 要求完成组数(默认是全部子组) */
  var subCount: Short = -1
  /** 学期学分分布 */
  var termCredits: String = _
  /** 课程属性 */
  var rank: Option[CourseRank] = None
  /** 开课学期 */
  var terms: Terms = Terms.empty
  /** 开课阶段 */
  var stage: Option[CalendarStage] = None

  def required: Boolean = {
    rank match
      case Some(r) => r.id == CourseRank.Compulsory
      case None => true
  }

  def autoAddup: Boolean = {
    rank match
      case Some(r) => r.id == CourseRank.Compulsory
      case None => true
  }

  def allowUnplanned: Boolean = {
    rank match
      case Some(r) => r.id == CourseRank.Selective || r.id == CourseRank.FreeSelective
      case None => false
  }

  def optional: Boolean = {
    rank match
      case Some(r) => r.id != CourseRank.Compulsory
      case None => false
  }

  override def name: String = {
    val sb = new StringBuilder()
    if (null != courseType) sb.append(courseType.name)
    givenName foreach { x => sb.append(" ").append(x) }
    sb.toString
  }

  def shortName: String = {
    givenName match
      case None => courseType.shortName.getOrElse(courseType.name)
      case Some(gn) => gn
  }

  def index(): Int = {
    var index = Strings.substringAfterLast(indexno, ".")
    if (Strings.isEmpty(index)) index = indexno
    var idx = Numbers.toInt(index)
    if (idx <= 0) idx = 1
    idx
  }

  def addGroup(group: AbstractCourseGroup): Unit = {
    group.parent = Some(this)
    children += group
  }

  def addCourse(planCourse: AbstractPlanCourse): Unit = {
    if (!planCourses.exists(_.course == planCourse.course)) {
      planCourse.group = this
      planCourses += planCourse
    }
  }

  def removeCourse(pc: PlanCourse): Unit = {
    planCourses -= pc
  }

  /**
   * 添加计划课程
   */
  def addCourses(planCourses: Iterable[AbstractPlanCourse]): Unit = {
    for (element <- planCourses) {
      addCourse(element)
    }
  }

  def follow(plan: CoursePlan): Unit = {
    this.plan = plan
    children.foreach { c =>
      c.asInstanceOf[AbstractCourseGroup].follow(plan)
    }
  }

  def parents(): Seq[CourseGroup] = {
    val ps = Collections.newBuffer[CourseGroup]
    var p = parent
    while (p.isDefined) {
      ps.insert(0, p.get)
      p = p.get.parent
    }
    ps.toSeq
  }

  def path(): Seq[CourseGroup] = {
    val ps = Collections.newBuffer[CourseGroup]
    var p = parent
    while (p.isDefined) {
      ps.insert(0, p.get)
      p = p.get.parent
    }
    ps.addOne(this)
    ps.toSeq
  }

  def getTermCourses(terms: Terms): collection.Seq[PlanCourse] = {
    if (Strings.isEmpty(null)) return planCourses.toList
    planCourses.filter(pc => pc.matchTerm(terms))
  }

  override def compare(o: CourseGroup): Int = {
    indexno.compareTo(o.indexno)
  }

  override def isLeaf: Boolean = {
    children.isEmpty && planCourses.isEmpty
  }

  override def getHours(natures: collection.Seq[TeachingNature]): Map[TeachingNature, Int] = {
    val natureMap = natures.map(x => (x.id.toString, x)).toMap

    val ratioMap = getHourRatioMap(natures)
    if (ratioMap.isEmpty) {
      val targetNature =
        if (this.courseType.practical) natures.find(_.id == TeachingNature.Practice).get else natures.find(_.id == TeachingNature.Theory).get
      Map(targetNature -> creditHours)
    } else {
      ratioMap.map(e => (e._1, (e._2 * creditHours).toInt))
    }
  }

  override def getHourRatioMap(natures: collection.Seq[TeachingNature]): Map[TeachingNature, Float] = {
    hourRatios match
      case None => Map.empty
      case Some(ratios) =>
        val natureMap = natures.map(x => (x.id.toString, x)).toMap
        val ratioMap = Collections.newMap[TeachingNature, Float]
        var sum = 0f
        for (r <- Strings.split(ratios, ",")) {
          val natureId = Strings.substringBefore(r, ":")
          val value = Strings.substringAfter(r, ":").toFloat
          sum += value
          natureMap.get(natureId) foreach { n =>
            ratioMap.put(n, value)
          }
        }
        for (n <- ratioMap.keySet) {
          ratioMap.put(n, ratioMap(n) / sum)
        }
        ratioMap.toMap
  }

  def isTermCreditsEmpty: Boolean = {
    if Strings.isEmpty(termCredits) then true
    else
      val credits = Strings.split(termCredits, ",").map(_.trim).toSet
      if credits.size == 1 then credits.head == "0"
      else false
  }

  def termCreditSeq: Seq[Double] = {
    if Strings.isEmpty(termCredits) then Seq.empty
    else
      Strings.split(termCredits, ",").map(_.toDouble).toSeq
  }

  override def toString: String = {
    s"${this.indexno} ${this.name}"
  }
}
