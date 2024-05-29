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

package org.openurp.edu.program.domain

import org.openurp.base.edu.model.Course
import org.openurp.base.std.model.Student
import org.openurp.edu.program.model.*

/** 培养计划提供者
 *
 * @author chaostone
 *
 */
trait CoursePlanProvider {
  /** 获得原始专业培养计划
   */
  def getMajorPlan(student: Student): Option[MajorPlan]

  /** 获得执行专业培养计划
   */
  def getExecutivePlan(student: Student): Option[ExecutivePlan]

  /** 获得单个学生的个人计划
   */
  def getStdPlan(student: Student): Option[StdPlan]

  /** 获得学生的计划
   *
   * @param std
   * @return
   */
  def getCoursePlan(std: Student): Option[CoursePlan]

  /** 查询学生对应的公共计划
   *
   * @param std
   * @return
   */
  def getSharePlan(std: Student): Option[SharePlan]

  /** 获取某个课程
   *
   * @param std
   * @param course
   * @return
   */
  def getPlanCourse(std: Student, course: Course): Option[PlanCourse]

  /** 查询学生的可选课程
   *
   * @param std
   * @return
   */
  def getCourses(std: Student): Seq[Course]
}
