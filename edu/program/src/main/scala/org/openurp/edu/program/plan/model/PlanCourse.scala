/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.program.plan.model

import org.beangle.commons.lang.time.WeekState
import org.beangle.data.model.LongIdEntity
import org.openurp.base.model.Department
import org.openurp.edu.base.code.model.ExamMode
import org.openurp.edu.base.model.Course
import org.openurp.edu.base.model.Terms

/**
 * 培养计划中的课程.<import org.openurp.edu.program.plan.model.CourseGroup
 * br>
 * 具体体现了课程、开课和审核要求三部分.
 *
 * @author chaostone
 */
trait PlanCourse extends LongIdEntity {

  /**
   * 查询课程.
   */
  def course: Course

  def course_=(c: Course)

  /**
   * 课程开设的学期.<br>
   * 格式一般为数字或者汉字，例如：5,6或者春、秋、春秋.
   */
  def terms: Terms

  def terms_=(t: Terms)

  /**
   * 课程组
   */
  def group: CourseGroup

  def group_=(g: CourseGroup)
  /**
   * 课程是否必修.
   */
  def compulsory: Boolean

  def compulsory_=(c: Boolean)
}

/**
 * 执行计划课程，例如MajorPlanCourse,SharePlanCourse
 */
trait ExecutePlanCourse {
  /**
   * 开课部门
   */
  var department: Department = _

  /**
   * 考核方式
   */
  var examMode: ExamMode = _

  /**
   * 开课周
   */
  var weekstate: WeekState = _
}
