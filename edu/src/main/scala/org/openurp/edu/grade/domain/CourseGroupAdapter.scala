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

package org.openurp.edu.grade.domain

import org.beangle.data.model.LongId
import org.openurp.base.edu.model.Terms
import org.openurp.code.edu.model.CourseType
import org.openurp.edu.program.model.{CourseGroup, CoursePlan, PlanCourse}

class CourseGroupAdapter(var coursePlan: CoursePlan) extends LongId with CourseGroup {

  private var groups: collection.Seq[CourseGroup] = coursePlan.groups.filter { g => g.parent == null }

  override def name: String = "plan"

  override def children: collection.Seq[CourseGroup] = groups

  override def credits: Float = coursePlan.credits

  override def clone: AnyRef = super.clone

  override def courseCount: Short = 0

  override def courseType: CourseType = null

  override def subCount: Short = -1

  override def parent: Option[CourseGroup] = None

  override def creditHours: Int = 0

  override def planCourses: collection.Seq[PlanCourse] = List.empty

  override def remark: Option[String] = None

  def planCourses(termList: collection.Seq[Integer]): collection.Seq[_ <: PlanCourse] = null

  def planCourses(terms: String): collection.Seq[_ <: PlanCourse] = null

  override def plan: CoursePlan = null

  override def termCredits: String = null

  override def compare(o: CourseGroup): Int = 0

  override def indexno: String = null

  override def autoAddup: Boolean = true

  /** 开课学期 */
  override def terms: Terms = Terms.empty

  override def hourRatios: Option[String] = None
}
