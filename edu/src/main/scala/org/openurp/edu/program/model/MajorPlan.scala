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

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.{MajorDirection, Terms}

import java.time.Instant

/**
 * @author chaostone
 */
class MajorPlan extends AbstractCoursePlan {
  def this(program: Program) = {
    this()
    this.program = program
    this.credits = program.credits
    this.creditHours = 0
    this.hourRatios = " "
    this.updatedAt = Instant.now
  }

  def this(program: Program, other: AbstractCoursePlan) = {
    this()
    this.program = program
    this.credits = other.credits
    this.creditHours = other.creditHours
    this.hourRatios = other.hourRatios
    this.updatedAt = Instant.now

    other.topGroups foreach { g =>
      new MajorCourseGroup(this, g.asInstanceOf[AbstractCourseGroup])
    }
  }
}

/**
 * 专业计划课程组.
 *
 * @author chaostone
 */
class MajorCourseGroup extends AbstractCourseGroup {

  /** 该组针对的专业方向 */
  var direction: Option[MajorDirection] = None

  /** 开课院系 */
  var departments: Option[String] = None

  def this(plan: MajorPlan, group: AbstractCourseGroup) = {
    this()
    this.plan = plan
    plan.groups.addOne(this)

    this.credits = group.credits
    this.creditHours = group.creditHours
    this.hourRatios = group.hourRatios
    this.weeks = group.weeks

    this.terms = group.terms
    this.termCredits = group.termCredits

    this.indexno = group.indexno
    this.courseType = group.courseType
    this.givenName = group.givenName

    this.rank = group.rank
    this.required = group.required
    this.subCount = group.subCount

    this.stage = group.stage
    this.remark = group.remark

    group match
      case m: MajorCourseGroup =>
        this.direction = m.direction
        this.departments = m.departments
      case e: ExecutiveCourseGroup => this.direction = e.direction
      case _ =>

    group.planCourses foreach { pc =>
      val epc = new MajorPlanCourse(this, pc)
      this.planCourses.addOne(epc)
    }
    group.children foreach { c =>
      val cc = new MajorCourseGroup(plan, c.asInstanceOf[AbstractCourseGroup])
      cc.parent = Some(this)
      this.children.addOne(cc)
      cc.plan = plan
      plan.groups.addOne(cc)
    }
  }
}

/**
 * 专业计划课程
 *
 */
class MajorPlanCourse extends AbstractPlanCourse, Executable, Remark {

  /** 建议修读学期 */
  var suggestTerms: Terms = Terms.empty

  def this(group: MajorCourseGroup, planCourse: PlanCourse) = {
    this()
    this.group = group
    this.idx = planCourse.idx
    this.course = planCourse.course
    this.terms = planCourse.terms
    this.compulsory = planCourse.compulsory

    planCourse match
      case e: Executable =>
        this.termText = e.termText
        this.weekstate = e.weekstate
        this.stage = e.stage
      case _ =>

    planCourse match
      case r: Remark => this.remark = r.remark
      case _ =>

    planCourse match
      case r: MajorPlanCourse => this.suggestTerms = r.suggestTerms
      case _ =>
  }
}
