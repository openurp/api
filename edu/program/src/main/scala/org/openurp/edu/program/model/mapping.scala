/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.program.model

import scala.reflect.runtime.universe

import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.bind.Mapping
import org.openurp.edu.program.plan.model.{ AbstractCourseGroup, AbstractPlanCourse, MajorCourseGroup, MajorCourseSubstitution, MajorPlan, MajorPlanCourse, ShareCourseGroup, SharePlan, SharePlanCourse, StdCourseGroup, StdCourseSubstitution, StdPlan, StdPlanCourse }

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("date")

    bind[AbstractCourseGroup].on(e => declare(
      e.indexno is (notnull, length(20)),
      e.courseType is notnull,
      e.remark is length(200)))

    bind[AbstractPlanCourse].on(e => declare(
      e.course is notnull,
      e.remark is length(200)))

    bind[MajorCourseGroup].on(e => declare(
      e.plan is target[MajorPlan],
      e.parent is target[MajorCourseGroup],
      e.children is depends(classOf[MajorCourseGroup], "parent"),
      e.planCourses is depends(classOf[MajorPlanCourse], "group"),
      e.termCredits is (notnull, length(40)),
      e.alias is length(100)))

    bind[MajorPlan].on(e => declare(
      e.program & e.beginOn & e.endOn are notnull,
      e.groups is depends(classOf[MajorCourseGroup], "plan"),
      e.remark is length(200)))

    bind[MajorPlanCourse].on(e => declare(
      e.group is target[MajorCourseGroup]))

    bind[ShareCourseGroup].on(e => declare(
      e.plan is target[SharePlan],
      e.parent is target[ShareCourseGroup],
      e.children is depends(classOf[ShareCourseGroup], "parent"),
      e.planCourses is depends(classOf[SharePlanCourse], "group")))

    bind[SharePlan].on(e => declare(
      e.project & e.education & e.fromGrade & e.toGrade & e.beginOn & e.endOn are notnull,
      e.groups is depends(classOf[ShareCourseGroup], "plan"),
      e.remark is length(200)))

    bind[SharePlanCourse].on(e => declare(
      e.group is target[ShareCourseGroup]))

    bind[StdCourseGroup].on(e => declare(
      e.plan is target[StdPlan],
      e.parent is target[StdCourseGroup],
      e.children is depends(classOf[StdCourseGroup], "parent"),
      e.planCourses is depends(classOf[StdPlanCourse], "group")))

    bind[StdPlan].on(e => declare(
      e.std & e.beginOn & e.endOn are notnull,
      e.groups is depends(classOf[StdCourseGroup], "plan"),
      e.remark is length(200)))

    bind[StdPlanCourse].on(e => declare(
      e.group is target[StdCourseGroup]))

    bind[MajorCourseSubstitution].on(e => declare(
      e.project & e.education are notnull,
      e.fromGrade & e.toGrade are (length(10), notnull))).table("major_course_subs")

    bind[StdCourseSubstitution].on(e => declare(
      e.std is notnull)).table("std_course_subs")
  }

}
