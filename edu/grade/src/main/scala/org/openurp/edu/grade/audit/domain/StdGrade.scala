/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.audit.domain

import org.openurp.edu.base.model.Course
import org.openurp.edu.grade.course.model.CourseGrade
import org.beangle.commons.collection.Collections

class StdGrade(val grades: Seq[CourseGrade]) {

  private val gradeMap = grades.groupBy(g => g.course)

  private val usedCourses = Collections.newSet[Course]

  private val noGradeCourses = Collections.newSet[Course]

  /**
   * 查询课程对应的成绩，不会被标记为usedCourses
   *
   */
  def getGrades(course: Course): Seq[CourseGrade] = {
    if (noGradeCourses.contains(course)) return List.empty
    gradeMap.get(course).getOrElse(List.empty)
  }

  /**
   * 使用课程课程拿成绩之后，会被标记为usedCourses
   */
  def useGrades(course: Course): Seq[CourseGrade] = {
    if (noGradeCourses.contains(course)) {
      List.empty
    } else {
      usedCourses += course
      gradeMap.get(course) match {
        case None    => List.empty
        case Some(l) => l.toList
      }
    }
  }

  /**
   * 拿到还未使用过的课程
   *
   */
  def restCourses: Set[Course] = {
    gradeMap.keySet -- usedCourses
  }

  /**
   * 获得一个课程的成绩，并且会标记该课程已被使用过
   *
   */
  def addNoGradeCourse(course: Course) {
    noGradeCourses += course
  }

  /**
   * 返回每个课程是否通过
   */
  def getCoursePassedMap(): Map[Long, Boolean] = {
    gradeMap.map {
      case (k, l) => k.id -> l.exists(_.passed)
    }
  }
}
