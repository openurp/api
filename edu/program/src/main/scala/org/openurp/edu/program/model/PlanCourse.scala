/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.program.model

import org.beangle.commons.lang.time.WeekState
import org.beangle.data.model.LongIdEntity
import org.openurp.base.model.Department
import org.openurp.code.edu.model.ExamMode
import org.openurp.edu.base.model.{CalendarStage, Course, Terms}

/**
 * 培养计划中的课程.<import org.openurp.edu.program.plan.model.CourseGroup
 * br>
 * 具体体现了课程、开课和审核要求三部分.
 * @author chaostone
 */
trait PlanCourse extends LongIdEntity {

  /**
   * 查询课程.
   */
  def course: Course

  def course_=(c: Course): Unit

  /**
   * 课程开设的学期.<br>
   * 格式一般为数字或者汉字，例如：5,6或者春、秋、春秋.
   */
  def terms: Terms

  def terms_=(t: Terms): Unit

  /**
   * 课程组
   */
  def group: CourseGroup

  def group_=(g: CourseGroup): Unit

  /**
   * 课程是否必修.
   */
  def compulsory: Boolean

  def compulsory_=(c: Boolean): Unit
}

/**
 * 执行计划课程，例如MajorPlanCourse,SharePlanCourse
 */
trait Executable {
  /** 开课部门 */
  var department: Department = _

  /** 考核方式 */
  var examMode: ExamMode = _

  /** 开课周 */
  var weekstate: WeekState = WeekState.Zero

  /** 开课阶段 */
  var stage: CalendarStage = _
}
