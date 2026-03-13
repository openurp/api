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
import org.openurp.edu.grade.domain.StdGrade.GradeList
import org.openurp.edu.grade.model.CourseGrade

object StdGrade {
  def filterGrades(grades: collection.Seq[CourseGrade]): Map[Course, GradeList] = {
    grades.sorted(PropertyOrdering.by("course,score desc")).groupBy(g => g.course) map { case (c, glist) =>
      val best = if (glist.head.passed) glist.head else glist.find(_.passed).getOrElse(glist.head)
      (c, GradeList(glist.exists(_.passed), best, glist.toSeq))
    }
  }

  case class GradeList(passed: Boolean, best: CourseGrade, all: Seq[CourseGrade])
}

class StdGrade(grades: collection.Seq[CourseGrade]) {

  private val gradeMap = StdGrade.filterGrades(grades)

  private val used = Collections.newSet[Course]

  /** 查询课程对应的成绩，不会被标记为usedCourses
   */
  def best(course: Course): CourseGrade = {
    if used.contains(course) then throw IllegalArgumentException(s"Course ${course.code} grade was consumed")
    else gradeMap.get(course).map(_.best).get
  }

  def getGrades(course: Course): Option[GradeList] = {
    if used.contains(course) then None else gradeMap.get(course)
  }

  def hasGrade(course: Course): Boolean = {
    if used.contains(course) then false else gradeMap.contains(course)
  }

  /** 使用课程课程拿成绩之后，会被标记为usedCourses
   */
  def consume(course: Course): Option[GradeList] = {
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
