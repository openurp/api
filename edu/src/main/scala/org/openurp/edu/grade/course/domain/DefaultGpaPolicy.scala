/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.course.domain

import org.openurp.base.edu.model.Course
import org.openurp.base.edu.model.Student
import org.openurp.edu.grade.course.model.CourseGrade
import org.openurp.edu.grade.course.model.StdGpa
import org.openurp.edu.grade.course.model.StdSemesterGpa
import org.openurp.edu.grade.course.model.StdYearGpa
import org.openurp.base.edu.model.Semester
import scala.collection.mutable.Buffer
import org.beangle.commons.collection.Collections
import java.time.Instant

class DefaultGpaPolicy extends GpaPolicy {

  var precision = 2

  var rounder: NumRounder = NumRounder.Normal

  def calcGa(grades: Iterable[CourseGrade]): Float = {
    rounder.round(WeightedMean.calcGa(grades), precision)
  }

  def calcGpa(grades: Iterable[CourseGrade]): Float = {
    rounder.round(WeightedMean.calcGpa(grades), precision)
  }

  def calc(std: Student, grades: Iterable[CourseGrade], statDetail: Boolean): StdGpa = {
    val stdGpa = new StdGpa(std)
    if (statDetail) {
      val gradesMap = Collections.newMap[Semester, Buffer[CourseGrade]]
      for (grade <- grades) {
        val semesterGrades = gradesMap.getOrElseUpdate(grade.semester, Collections.newBuffer)
        semesterGrades += grade
      }

      val yearGradeMap = Collections.newMap[String, Buffer[CourseGrade]]
      gradesMap foreach {
        case (semester, semesterGrades) =>
          val stdTermGpa = new StdSemesterGpa()
          stdTermGpa.semester = semester
          stdGpa.add(stdTermGpa)
          val yearGrades = yearGradeMap.getOrElseUpdate(semester.schoolYear, Collections.newBuffer)
          yearGrades ++= semesterGrades

          stdTermGpa.gpa = this.calcGpa(semesterGrades)
          stdTermGpa.ga = this.calcGa(semesterGrades)
          stdTermGpa.gradeCount = semesterGrades.size
          val stats = statCredits(semesterGrades)
          stdTermGpa.totalCredits = stats(0)
          stdTermGpa.credits = stats(1)
      }
      yearGradeMap foreach {
        case (year, yearGrades) =>
          val stdYearGpa = new StdYearGpa()
          stdYearGpa.schoolYear = year
          stdGpa.add(stdYearGpa)
          stdYearGpa.gpa = this.calcGpa(yearGrades)
          stdYearGpa.ga = this.calcGa(yearGrades)
          stdYearGpa.gradeCount = yearGrades.size
          val stats = statCredits(yearGrades)
          stdYearGpa.totalCredits = stats(0)
          stdYearGpa.credits = stats(1)
      }
    }
    stdGpa.gpa = this.calcGpa(grades)
    stdGpa.ga = this.calcGa(grades)

    val courseMap = Collections.newMap[Course, CourseGrade]
    for (grade <- grades) {
      val add = courseMap.get(grade.course) match {
        case None        => true
        case Some(exist) => !exist.passed
      }
      if (add) {
        courseMap.put(grade.course, grade)
      }
    }
    stdGpa.gradeCount = courseMap.size
    val totalStats = statCredits(courseMap.values)
    stdGpa.totalCredits = totalStats(0)
    stdGpa.credits = totalStats(1)
    stdGpa.updatedAt = Instant.now
    stdGpa
  }

  private def statCredits(grades: Iterable[CourseGrade]): Array[Float] = {
    var credits = 0f
    var all = 0f
    for (grade <- grades) {
      if (grade.passed) credits += grade.course.credits
      all += grade.course.credits
    }
    Array(all, credits)
  }
}
