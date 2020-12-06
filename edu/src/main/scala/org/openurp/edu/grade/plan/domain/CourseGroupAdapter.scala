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
package org.openurp.edu.grade.plan.domain

import org.beangle.data.model.LongId
import org.openurp.base.edu.code.model.CourseType
import org.openurp.base.edu.model.Terms
import org.openurp.edu.program.model.{CourseGroup, CoursePlan, PlanCourse}

class CourseGroupAdapter(var coursePlan: CoursePlan) extends LongId with CourseGroup {

  private var groups: collection.Seq[CourseGroup] = coursePlan.groups.filter { g => g.parent == null }

  override def name: String = "plan"

  def children: collection.Seq[CourseGroup] = groups

  def credits: Float = coursePlan.credits

  override def clone: AnyRef = super.clone

  def courseCount: Short = 0

  def courseType: CourseType = null

  def subCount: Short = {
    -1
  }

  def addChildGroup(arg0: CourseGroup): Unit = {
  }

  def addPlanCourse(arg0: PlanCourse): Unit = {
  }

  def parent: Option[CourseGroup] = None

  def parent_=(arg0: Option[CourseGroup]): Unit = {
  }

  def planCourses: collection.Seq[PlanCourse] = List.empty

  def remark: Option[String] = None

  def remark_=(arg0: Option[String]): Unit = {
  }

  def compulsory: Boolean = false

  def courseCount_=(arg0: Int): Unit = {
  }

  def courseType_=(arg0: CourseType): Unit = {
  }

  def credits(arg0: Float): Unit = {
  }

  def subCount_=(arg0: Int): Unit = {
  }

  def updateCoursePlan(arg0: CoursePlan): Unit = {
  }

  def removePlanCourse(course: PlanCourse): Unit = {
  }

  def statCreditAndHour(termsCount: Int): Unit = {
  }

  def creditPerTerms: String = {
    throw new UnsupportedOperationException("CoursePlanGroupAdapter.CreditPerTerms没有实现")
  }

  def creditPerTerms(arg0: String): Unit = {
    throw new UnsupportedOperationException("CoursePlanGroupAdapter.CreditPerTerms没有实现")
  }

  def creditList: collection.Seq[String] = null

  def credits(terms: collection.Seq[Integer]): Float = 0

  def groupCourses: collection.Seq[_ <: PlanCourse] = null

  def parentCourseType: CourseType = null

  def planCourses(termList: collection.Seq[Integer]): collection.Seq[_ <: PlanCourse] = null

  def planCourses(terms: String): collection.Seq[_ <: PlanCourse] = null

  def isSameGroup(`object`: AnyRef): Boolean = false

  def groupCourses_=(groupCourses: collection.Seq[_ <: PlanCourse]): Unit = {
  }

  def plan: CoursePlan = null

  def plan_=(plan: CoursePlan): Unit = {
  }

  def children_=(children: collection.Seq[CourseGroup]): Unit = {
  }

  def planCourses_=(planCourses: collection.Seq[PlanCourse]): Unit = {
  }

  def addPlanCourses(planCourses: collection.Seq[PlanCourse]): Unit = {
  }

  def termCredits: String = null

  def termCredits(termCredits: String): Unit = {
  }

  override def compare(o: CourseGroup): Int = 0

  def indexno: String = null

  def indexno_=(indexno: String): Unit = {
  }

  override def autoAddup: Boolean = true

  /**开课学期*/
  override def terms:Terms=Terms.empty
}
