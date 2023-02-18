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
import org.openurp.base.edu.code.CourseType
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
  override def getExecutionPlan(student: Student): Option[ExecutionPlan] = {
    programProvider.getProgram(student) match {
      case Some(p) => getExecutionPlan(p, student)
      case None => None
    }
  }

  /**
   * 获得单个学生的个人计划
   */
  def getStdPlan(student: Student): Option[StdPlan] = {
    entityDao.findBy(classOf[StdPlan], "std", List(student)).headOption
  }

  /**
   * 获得学生的计划
   *
   * @param std
   * @return
   */
  def getCoursePlan(std: Student): Option[CoursePlan] = {
    getStdPlan(std) match {
      case Some(stdPlan) => Some(stdPlan)
      case None =>
        programProvider.getProgram(std) match {
          case Some(p) =>
            getExecutionPlan(p, std).orElse(getMajorPlan(p))
          case None => None
        }
    }
  }

  private def getMajorPlan(p: Program): Option[MajorPlan] = {
    entityDao.findBy(classOf[MajorPlan], "program", List(p)).headOption
  }

  private def getExecutionPlan(p: Program, student: Student): Option[ExecutionPlan] = {
    val plans = entityDao.findBy(classOf[ExecutionPlan], "program", List(p))
    val matched = plans.filter { p =>
      DefaultProgramMatcher.departMatched(p.department, student.state.get.department) &&
        (p.stdType.isEmpty || p.stdType.get == student.stdType) &&
        (p.campus.isEmpty || p.campus.get == student.state.get.campus)
    }
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
    if (null == planCourse) { //search share plan
      val grade = java.lang.Integer.valueOf(std.state.get.grade.code.substring(0, 4))
      val builder = OqlBuilder.from[PlanCourse](classOf[SharePlan].getName, "sp").join("sp.groups", "spg")
        .join("spg.planCourses", "spgp")
        .where("spgp.course=:course", course)
        .where("sp.project=:project", std.project)
        .where("year(sp.beginOn)<=:grade and (sp.endOn is null or year(sp.endOn)>=:grade)", grade)
        .select("spgp")
        .cacheable()
      entityDao.search(builder).headOption
    } else {
      Some(planCourse)
    }
  }

}
