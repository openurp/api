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

import org.beangle.commons.collection.Collections
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.edu.model.Course
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.CourseType
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
   * 获得学生的计划
   *
   * @param std
   * @return
   */
  override def getCoursePlan(std: Student): Option[CoursePlan] = {
    programProvider.getProgram(std) match {
      case Some(p) => getExecutivePlan(p, std).orElse(getMajorPlan(p))
      case None => None
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

  /** 查询学生的可选课程
   *
   * @param std
   * @return
   */
  override def getCourses(std: Student): Seq[Course] = {
    val courses = Collections.newSet[Course]
    val emptyCourseTypes = Collections.newSet[CourseType]
    //添加计划内课程
    getCoursePlan(std) foreach { plan =>
      for (group <- plan.groups) {
        if (group.planCourses.isEmpty && group.children.isEmpty) {
          emptyCourseTypes += group.courseType
        } else {
          group.planCourses foreach { pc => courses.addOne }
          if !group.autoAddup then emptyCourseTypes += group.courseType
        }
      }
    }
    //添加公共课程
    val spQuery = OqlBuilder.from(classOf[SharePlan], "sp")
    spQuery.where("sp.project=:project", std.project)
    spQuery.where("sp.level=:level and sp.eduType =:eduType", std.level, std.eduType)
    spQuery.where(":grade between sp.fromGrade.code and sp.toGrade.code", std.state.get.grade.code)
    entityDao.search(spQuery) foreach { sp =>
      for (cg <- sp.groups; planCourse <- cg.planCourses) {
        courses.add(planCourse.course)
      }
      sp.groups foreach { cg => emptyCourseTypes.subtractOne(cg.courseType) }
    }
    //添加其他课程库的公共课程
    if (emptyCourseTypes.nonEmpty) {
      val typeQuery = OqlBuilder.from(classOf[CourseType], "ct").where("ct.parent in(:parents)", emptyCourseTypes)
      emptyCourseTypes ++= entityDao.search(typeQuery)
      val q = OqlBuilder.from(classOf[Course], "c")
      q.where("c.project=:project", std.project)
      q.where("c.endOn is null and c.courseType in(:courseTypes)", emptyCourseTypes)
      courses.addAll(entityDao.search(q))
    }
    courses.toSeq
  }
}
