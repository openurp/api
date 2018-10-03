/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.audit.domain

import org.beangle.data.model.LongId
import org.openurp.edu.base.code.model.CourseType
import org.openurp.edu.program.plan.model.{ CourseGroup, CoursePlan, PlanCourse }

class CourseGroupAdapter(var coursePlan: CoursePlan) extends LongId with CourseGroup {

  private var groups: Seq[CourseGroup] = coursePlan.groups.filter { g => g.parent == null }

  name = "plan"

  def children: Seq[CourseGroup] = groups

  def credits: Float = coursePlan.credits

  override def clone: AnyRef = super.clone

  def courseNum: Short = 0

  def courseType: CourseType = null

  def groupNum: Short = {
    -1
  }

  def addChildGroup(arg0: CourseGroup) {
  }

  def addPlanCourse(arg0: PlanCourse) {
  }

  def parent: Option[CourseGroup] = None
  def parent_=(arg0: Option[CourseGroup]) {
  }
  def planCourses: Seq[PlanCourse] = List.empty

  def remark: Option[String] = None
  def remark_=(arg0: Option[String]) {
  }

  def compulsory: Boolean = false

  def courseNum_=(arg0: Int) {
  }

  def courseType_=(arg0: CourseType) {
  }

  def credits(arg0: Float) {
  }

  def groupNum_=(arg0: Int) {
  }

  def updateCoursePlan(arg0: CoursePlan) {
  }

  def removePlanCourse(course: PlanCourse) {
  }

  def statCreditAndHour(termsCount: Int) {
  }

  def creditPerTerms: String = {
    throw new UnsupportedOperationException("CoursePlanGroupAdapter.CreditPerTerms没有实现")
  }

  def creditPerTerms(arg0: String) {
    throw new UnsupportedOperationException("CoursePlanGroupAdapter.CreditPerTerms没有实现")
  }

  def creditList: Seq[String] = null

  def credits(terms: Seq[Integer]): Float = 0

  def groupCourses: Seq[_ <: PlanCourse] = null

  def parentCourseType: CourseType = null

  def planCourses(termList: Seq[Integer]): Seq[_ <: PlanCourse] = null

  def planCourses(terms: String): Seq[_ <: PlanCourse] = null

  def isSameGroup(`object`: AnyRef): Boolean = false

  def groupCourses_=(groupCourses: Seq[_ <: PlanCourse]) {
  }

  def plan: CoursePlan = null

  def plan_=(plan: CoursePlan) {
  }

  def children_=(children: Seq[CourseGroup]) {
  }

  def planCourses_=(planCourses: Seq[PlanCourse]) {
  }

  def addPlanCourses(planCourses: Seq[PlanCourse]) {
  }

  def termCredits: String = null

  def termCredits(termCredits: String) {
  }

  override def compare(o: CourseGroup): Int = 0

  def indexno: String = null

  def indexno_=(indexno: String) {
  }

  def index: Int = 0
}
