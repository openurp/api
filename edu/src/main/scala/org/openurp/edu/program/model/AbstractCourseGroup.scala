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

import org.beangle.commons.lang.{Numbers, Strings}
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Hierarchical, Remark}
import org.openurp.base.edu.code.CourseType
import org.openurp.base.edu.model.Terms

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * 课程设置中的课程组 </p>
 *
 * @author chaostone
 * @since 2009
 */
abstract class AbstractCourseGroup extends LongId with CourseGroup with Cloneable with Hierarchical[CourseGroup] with Remark {
  /**
   * 计划
   */
  var plan: CoursePlan = _
  /**
   * 计划课程列表
   */
  var planCourses: mutable.Buffer[PlanCourse] = new ListBuffer[PlanCourse]

  /**
   * 自定义别名
   */
  var givenName: Option[String] = None

  /**
   * 课程类别
   */
  var courseType: CourseType = _

  /**
   * 要求学分
   */
  var credits: Float = _

  /** 课时 */
  var creditHours: Int = _

  /** 课时比例 */
  var hourRatios: Option[String] = None

  /**
   * 要求完成组数
   */
  var subCount: Short = _

  /**
   * 要求门数
   */
  var courseCount: Short = _

  /**
   * 学期学分分布
   */
  var termCredits: String = _

  /** 自动累加学分 */
  var autoAddup: Boolean = _

  /** 开课学期 */
  var terms: Terms = Terms.empty

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
    if (planCourses.exists(_.course == planCourse.course)) return
      planCourse.group = this
    planCourses += planCourse
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

  def planCourses(terms: Terms): collection.Seq[PlanCourse] = {
    if (Strings.isEmpty(null)) return planCourses.toList
    planCourses.filter(pc => pc.terms.matches(terms))
  }

  override def compare(o: CourseGroup): Int = {
    indexno.compareTo(o.indexno)
  }

}
