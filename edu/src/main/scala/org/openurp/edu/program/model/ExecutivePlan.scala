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

import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.Direction
import org.openurp.base.model.Department

import java.time.Instant

/** 执行计划
 *
 * @author chaostone
 */
class ExecutivePlan extends AbstractCoursePlan, CoursePlan {

  /** 部门(培养方案的部门或者子部门) */
  var department: Department = _

  def this(other: AbstractCoursePlan) = {
    this()
    this.program = other.program
    this.credits = other.credits
    this.creditHours = other.creditHours
    this.hourRatios = other.hourRatios
    this.department = other.program.department
    this.updatedAt = Instant.now

    other.topGroups foreach { g =>
      new ExecutiveCourseGroup(this, g.asInstanceOf[AbstractCourseGroup])
    }
  }
}

/** 执行计划课程组
 *
 * @author chaostone
 */
class ExecutiveCourseGroup extends AbstractCourseGroup {

  /** 该组针对的专业方向 */
  var direction: Option[Direction] = None

  def this(plan: ExecutivePlan, group: AbstractCourseGroup) = {
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
      case m: MajorCourseGroup => this.direction = m.direction
      case e: ExecutiveCourseGroup => this.direction = e.direction
      case _ =>

    group.planCourses foreach { pc =>
      val epc = new ExecutivePlanCourse(this, pc)
      this.planCourses.addOne(epc)
    }
    group.children foreach { c =>
      val cc = new ExecutiveCourseGroup(plan, c.asInstanceOf[AbstractCourseGroup])
      cc.parent = Some(this)
      this.children.addOne(cc)
      cc.plan = plan
      plan.groups.addOne(cc)
    }
  }
}

/** 执行计划课程
 */
class ExecutivePlanCourse extends AbstractPlanCourse, Executable, Remark {

  def this(group: ExecutiveCourseGroup, planCourse: PlanCourse) = {
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
      case _ =>

    planCourse match
      case r: Remark => this.remark = r.remark
      case _ =>
  }

}
