/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.program.plan.model

import scala.collection.mutable.{ Buffer, ListBuffer }

import org.beangle.commons.lang.{ Numbers, Strings }
import org.beangle.data.model.{ LongId, Named }
import org.openurp.edu.base.code.model.CourseType
import org.openurp.edu.base.model.Terms
/**
 * 课程设置中的课程组 </p>
 *
 * @author chaostone
 * @since 2009
 */
trait AbstractCourseGroup extends LongId with CourseGroup with Named with Cloneable with Ordered[CourseGroup] {
  /**
   * 计划
   */
  var plan: CoursePlan = _

  /**
   * 上级组
   */
  var parent: Option[CourseGroup] = _

  /**
   * 下级组列表
   */
  var children: Buffer[AbstractCourseGroup] = new ListBuffer[AbstractCourseGroup]

  /**
   * 计划课程列表
   */
  var planCourses: Buffer[PlanCourse] = new ListBuffer[PlanCourse]

  /**
   * 课程类别
   */
  var courseType: CourseType = _

  /**
   * 要求学分
   */
  var credits: Float = _

  /**
   * 要求完成组数
   */
  var groupNum: Short = _

  /**
   * 要求门数
   */
  var courseNum: Short = _

  /**
   * 备注
   */
  var remark: Option[String] = _

  /**
   * 学期学分分布
   */
  var termCredits: String = _

  /**
   * index no
   */
  var indexno: String = _

  def index(): Int = {
    var index = Strings.substringAfterLast(indexno, ".")
    if (Strings.isEmpty(index)) index = indexno
    var idx = Numbers.toInt(index)
    if (idx <= 0) idx = 1
    idx
  }

  def compulsory: Boolean = {
    val requiredGroupNum = if (groupNum == -1) children.size else groupNum
    (requiredGroupNum == children.size && !planCourses.exists(p => !p.compulsory))
  }

  def addGroup(group: AbstractCourseGroup) {
    group.parent = Some(this)
    children += group
  }

  def addCourse(planCourse: AbstractPlanCourse): Unit = {
    if (planCourses.exists(_.course == planCourse.course)) return
    planCourse.group = this
    planCourses += planCourse
  }

  def removeCourse(pc: PlanCourse) {
    planCourses -= pc
  }

  /**
   * 添加计划课程
   */
  def addCourses(planCourses: Iterable[AbstractPlanCourse]) {
    for (element <- planCourses) {
      addCourse(element)
    }
  }

  def follow(plan: CoursePlan) {
    this.plan = plan
    children.foreach { c =>
      c.follow(plan)
    }
  }

  def planCourses(terms: Terms): Seq[PlanCourse] = {
    if (Strings.isEmpty(null)) return planCourses.toList
    planCourses.filter(pc => pc.terms.matches(terms))
  }

  override def compare(o: CourseGroup): Int = {
    indexno.compareTo(o.indexno)
  }
}

