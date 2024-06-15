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
  var rank: CourseRank = _
  /** 开课学期 */
  var terms: Terms = Terms.empty
  /** 开课阶段 */
  var stage: Option[CalendarStage] = None

  def required: Boolean = rank.id == CourseRank.Compulsory

  def autoAddup: Boolean = rank.id == CourseRank.Compulsory

  def allowUnplanned: Boolean = rank.id == CourseRank.Selective || rank.id == CourseRank.FreeSelective

  override def name: String = {
    val sb = new StringBuilder()
    if (null != courseType) sb.append(courseType.name)
    givenName foreach { x => sb.append(" ").append(x) }
    sb.toString
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

  def getHours(natures: Seq[TeachingNature]): Map[TeachingNature, Int] = {
    hourRatios match
      case None => Map(natures.head -> creditHours)
      case Some(r) =>
        val ratios = Strings.split(r, ":").map(_.toFloat)
        val total = ratios.sum
        val rs = ratios.indices.map { x => (natures(x) -> (creditHours * (ratios(x) / total)).toInt) }
        rs.toMap
  }
}
