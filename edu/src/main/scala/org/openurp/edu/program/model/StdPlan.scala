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

import org.openurp.base.std.model.Student

import java.time.Instant

/**
 * 个人计划
 */
class StdPlan extends AbstractCoursePlan {

  /** 学生 */
  var std: Student = _

  def this(std: Student, other: AbstractCoursePlan) = {
    this()
    this.std = std
    this.program = other.program
    this.credits = other.credits
    this.creditHours = other.creditHours
    this.hourRatios = other.hourRatios
    this.updatedAt = Instant.now

    other.topGroups foreach { g =>
      new StdCourseGroup(this, g.asInstanceOf[AbstractCourseGroup])
    }
  }
}

class StdCourseGroup extends AbstractCourseGroup {

  def this(plan: StdPlan, group: AbstractCourseGroup) = {
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

    group.planCourses foreach { pc =>
      val epc = new StdPlanCourse(this, pc)
      this.planCourses.addOne(epc)
    }
    group.children foreach { c =>
      val cc = new StdCourseGroup(plan, c.asInstanceOf[AbstractCourseGroup])
      cc.parent = Some(this)
      this.children.addOne(cc)
      cc.plan = plan
      plan.groups.addOne(cc)
    }
  }
}

class StdPlanCourse extends AbstractPlanCourse {

  def this(group: StdCourseGroup, planCourse: PlanCourse) = {
    this()
    this.group = group
    this.idx = planCourse.idx
    this.course = planCourse.course
    this.terms = planCourse.terms
    this.compulsory = planCourse.compulsory
  }
}
