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

package org.openurp.edu.grade.course.domain

import org.openurp.base.edu.model.Course
import org.openurp.edu.grade.course.model.CourseGrade
import org.openurp.edu.program.model.AlternativeCourse

/**
 * 成绩比较器
 */
object GradeComparator {

  /**
   * Return true if first better then second.
   * 先比较绩点，后比较分数，最后按照是否通过比较
   *
   * @param first NotNull
   * @param second
   */
  def betterThan(first: CourseGrade, second: CourseGrade): Boolean = {
    if (null == second) return true
    val gp1 = first.gp.getOrElse(0f)
    val gp2 = second.gp.getOrElse(0f)
    val gpResult = java.lang.Float.compare(gp1, gp2)
    if (0 != gpResult) return gpResult > 0
    val score1 = first.score.getOrElse(0f)
    val score2 = second.score.getOrElse(0f)
    val scoreResult = java.lang.Float.compare(score1, score2)
    if (0 != scoreResult) return scoreResult > 0
    first.passed
  }

  /**
   * 是否替代成功 <br>
   * 先比较绩点，后比较分数，最后按照是否通过比较
   *
   * @param altCourse
   * @param grades
   */
  def isSubstitute(altCourse: AlternativeCourse, grades: collection.Map[Course, CourseGrade]): Boolean = {
    var existOrigGrade = false
    var gpa1 = 0f
    var ga1 = 0f
    var credit1 = 0f
    var passed1 = 0
    for (course <- altCourse.olds) {
      grades.get(course) foreach { grade =>
        if (grade.passed) passed1 += 1
        grade.gp foreach { gp => gpa1 += grade.course.credits * gp }
        grade.score foreach { score => ga1 += grade.course.credits * score }
        existOrigGrade = true
      }
      credit1 += course.credits
    }
    var fullGrade2 = true
    var gpa2 = 0f
    var ga2 = 0f
    var credit2 = 0f
    var passed2 = 0
    for (course <- altCourse.news) {
      val grade = grades.get(course).getOrElse(null)
      if (null != grade) {
        if (grade.passed) passed2 += 1
        grade.gp foreach { gp => gpa2 += grade.course.credits * gp }
        grade.score foreach { score => ga2 += grade.course.credits * score }
      } else {
        fullGrade2 = false
      }
      credit2 += course.credits
    }

    var success = false
    if (!existOrigGrade && fullGrade2) {
      success = true
    } else {
      if ((fullGrade2) && (credit1 > 0 && credit2 > 0)) {
        // 优先比较绩点，其次比较分数，最后比较是否通过.
        var gpaCompare = 0
        if (gpa1 > 0 || gpa2 > 0) {
          gpaCompare = java.lang.Float.compare(gpa1 / credit1, gpa2 / credit2)
        }
        if (0 == gpaCompare && (ga1 > 0 || ga2 > 0)) {
          gpaCompare = java.lang.Float.compare(ga1 / credit1, ga2 / credit2)
        }
        if (0 == gpaCompare) gpaCompare = passed1 - passed2
        success = gpaCompare <= 0
      }
    }
    //    if (!success && existOrigGrade && fullGrade2) {
    //      altCourse.news foreach (grades.remove(_))
    //    }
    success
  }
}
