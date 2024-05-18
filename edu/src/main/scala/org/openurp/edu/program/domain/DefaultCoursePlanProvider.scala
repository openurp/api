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

import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.edu.model.Course
import org.openurp.base.std.model.Student
import org.openurp.edu.program.model.*

class DefaultCoursePlanProvider extends CoursePlanProvider {

  var programProvider: ProgramProvider = _
  var entityDao: EntityDao = _

  /**
   * 获得原始专业培养计划
   */
  override def getMajorPlan(student: Student): Option[MajorPlan] = {
    programProvider.getProgram(student) match {
      case Some(p) => getMajorPlan(p)
      case None => None
    }
  }

  /**
   * 获得专业培养计划
   */
  override def getExecutivePlan(student: Student): Option[ExecutivePlan] = {
    programProvider.getProgram(student) match {
      case Some(p) => getExecutivePlan(p, student)
      case None => None
    }
  }

  /**
   * 获得单个学生的个人计划
   */
  override def getStdPlan(student: Student): Option[StdPlan] = {
    entityDao.findBy(classOf[StdPlan], "std", List(student)).headOption
  }

  /**
   * 获得学生的计划
   *
   * @param std
   * @return
   */
  override def getCoursePlan(std: Student): Option[CoursePlan] = {
    getStdPlan(std) match {
      case Some(stdPlan) => Some(stdPlan)
      case None =>
        programProvider.getProgram(std) match {
          case Some(p) => getExecutivePlan(p, std).orElse(getMajorPlan(p))
          case None => None
        }
    }
  }

  override def getSharePlan(std: Student): Option[SharePlan] = {
    val query = OqlBuilder.from(classOf[SharePlan], "sp")
    query.where("sp.project=:project", std.project)
    query.where("sp.level=:level and sp.eduType =:eduType", std.level, std.eduType)
    query.where(":grade between sp.fromGrade.code and sp.toGrade.code", std.state.get.grade.code)
    query.cacheable()
    entityDao.search(query).headOption
  }

  private def getMajorPlan(p: Program): Option[MajorPlan] = {
    entityDao.findBy(classOf[MajorPlan], "program", List(p)).headOption
  }

  private def getExecutivePlan(p: Program, student: Student): Option[ExecutivePlan] = {
    val plans = entityDao.findBy(classOf[ExecutivePlan], "program", List(p))
    val matched = plans.filter { p => DefaultProgramMatcher.departMatched(p.department, student.state.get.department) }
    matched.headOption
  }

  override def getPlanCourse(std: Student, course: Course): Option[PlanCourse] = {
    val plan = getCoursePlan(std).orNull
    var planCourse: PlanCourse = null
    if (null != plan) { //search major or std plan
      for (cg <- plan.groups; if (cg != null && planCourse == null)) {
        cg.planCourses.find(_.course == course) foreach (x => planCourse = x)
      }
    }
    //不能查询公共课程，类型不匹配，SharePlanCourse is not a PlanCourse
    Option(planCourse)
  }

}
