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

import org.beangle.commons.collection.Collections
import org.openurp.base.edu.model.Course
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.CourseType
import org.openurp.edu.grade.model.AuditPlanResult
import org.openurp.edu.program.model.{CourseGroup, CoursePlan, SharePlan}

class AuditPlanContext(val std: Student, val coursePlan: CoursePlan, val sharePlan: Option[SharePlan],
                       val stdGrade: StdGrade, val listeners: collection.Seq[AuditPlanListener]) {

  var result: AuditPlanResult = _

  var typeMatchPolicy: AuditTypeMatchPolicy = DefaultAuditTypeMatchPolicy

  val params = Collections.newMap[String, Any]

  var shareCourses: Map[Course, CourseType] = sharePlan.map(_.course2Types).getOrElse(Map.empty)

  def getParam[T](paramName: String, clazz: Class[T]): T = {
    params.get(paramName).orNull.asInstanceOf[T]
  }

  def getGroup(course: Course, suggestType: CourseType): Option[CourseGroup] = {
    coursePlan.getGroup(course) match
      case Some(g) => Some(g)
      case None =>
        //按照建议的类别，然后按照公共课程，最后实在不行按照课程的类别
        getGroupByType(Some(suggestType)) match
          case Some(g) => Some(g)
          case None =>
            getGroupBySharePlan(course) match
              case None => getGroupByType(course.courseType)
              case Some(g) => Some(g)
  }

  private def getGroupByType(suggestType: Option[CourseType]): Option[CourseGroup] = {
    suggestType match
      case None => None
      case Some(ct) =>
        //按照建议的类别，不行则，按照课程类别的上级
        coursePlan.getGroup(ct.name) match
          case Some(g) => if typeMatchPolicy.allowMatchType(g) then Some(g) else getGroupByType(ct.parent)
          case None => getGroupByType(ct.parent)
  }

  private def getGroupBySharePlan(course: Course): Option[CourseGroup] = {
    shareCourses.get(course) match
      case Some(ct) => coursePlan.getGroup(ct.name)
      case None => None
  }

}
