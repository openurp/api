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

import org.beangle.commons.bean.orderings.PropertyOrdering
import org.beangle.commons.collection.Collections
import org.openurp.base.edu.model.Course
import org.openurp.edu.grade.model.CourseGrade

object StdGrade {
  def filterGrades(grades: collection.Seq[CourseGrade]): Map[Course, CourseGrade] = {
    grades.sorted(PropertyOrdering.by("course,score desc")).groupBy(g => g.course) map { case (c, glist) =>
      if (glist.head.passed) (c, glist.head)
      else (c, glist.find(_.passed).getOrElse(glist.head))
    }
  }
}

class StdGrade(grades: collection.Seq[CourseGrade]) {

  private val gradeMap = StdGrade.filterGrades(grades)

  private val used = Collections.newSet[Course]

  /** 查询课程对应的成绩，不会被标记为usedCourses
   */
  def getGrade(course: Course): Option[CourseGrade] = {
    if used.contains(course) then None else gradeMap.get(course)
  }

  def hasGrade(course: Course): Boolean = {
    if used.contains(course) then false else gradeMap.contains(course)
  }

  /** 使用课程课程拿成绩之后，会被标记为usedCourses
   */
  def useGrade(course: Course): Option[CourseGrade] = {
    if (used.contains(course)) {
      None
    } else {
      used += course
      gradeMap.get(course)
    }
  }

  /** 拿到还未使用过的课程
   */
  def restCourses: Set[Course] = {
    gradeMap.keySet -- used
  }
}
