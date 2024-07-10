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

import org.beangle.commons.lang.time.WeekState
import org.beangle.data.model.LongIdEntity
import org.openurp.base.edu.model.{Course, CourseJournal, Terms}

/**
 * 培养计划中的课程.<import org.openurp.edu.program.plan.model.CourseGroup
 * br>
 * 具体体现了课程、开课和审核要求三部分.
 *
 * @author chaostone
 */
trait PlanCourse extends LongIdEntity {

  /** 查询课程. */
  def course: Course

  /**
   * 课程组
   */
  def group: CourseGroup

  /**
   * 课程是否必修.
   */
  def compulsory: Boolean

  def credits: Float = {
    course.getCredits(group.plan.program.level)
  }

  def terms: Terms

  def idx: Short

  def matchTerm(terms: Terms): Boolean

  /** 查看对应的课程记录 */
  def journal: CourseJournal = {
    course.getJournal(group.plan.program.grade)
  }
}

/**
 * 执行计划课程，例如MajorPlanCourse,SharePlanCourse
 */
trait Executable {

  def compulsory: Boolean

  def course: Course

  /** 开课学期文本 */
  var termText: Option[String] = None

  /** 开课周 */
  var weekstate: WeekState = WeekState.Zero
}
