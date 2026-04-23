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
import org.beangle.commons.lang.annotation.beta
import org.openurp.base.edu.model.Course
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{CourseTakeType, GradeType}
import org.openurp.edu.grade.model.{CourseGrade, StdGpa, StdSemesterGpa, StdYearGpa}

import java.time.Instant
import scala.collection.mutable

@beta
object GpaCalculator {
  /** 加权平均值 */
  object WeightedMean {

    def calcScore(grades: collection.Iterable[CourseGrade]): BigDecimal = {
      if grades.isEmpty then BigDecimal(0)
      else
        var credits: Int = 0
        var creditGas: Long = 0L
        val level = grades.head.std.level
        for (grade <- grades) {
          if (grade.score.isDefined || !grade.passed) {
            val score = (grade.score.getOrElse(0f) * 1000).toLong
            val credit = (grade.course.getCredits(level) * 100).toInt
            credits += credit
            creditGas += credit * score
          }
        }
        if (credits == 0) BigDecimal(0) else BigDecimal(creditGas) / BigDecimal(credits * 1000)
    }

    def calcGpa(grades: collection.Iterable[CourseGrade]): BigDecimal = {
      if grades.isEmpty then 0f
      else
        var credits = 0
        var creditGps = 0L
        val level = grades.head.std.level
        for (grade <- grades if grade.gp.isDefined) {
          val credit = (grade.course.getCredits(level) * 100).toInt
          credits += credit
          creditGps += credit * (grade.gp.get * 100).toInt
        }
        if (credits == 0) BigDecimal(0) else BigDecimal(creditGps) / BigDecimal(credits * 100)
    }

  }

  /** 算术平均值 */
  object ArithmeticMean {

    def calcScore(grades: collection.Iterable[CourseGrade]): BigDecimal = {
      if grades.isEmpty then BigDecimal(0)
      else
        var credits: Int = 0
        var creditGas: Long = 0L
        for (grade <- grades) {
          if (grade.score.isDefined || !grade.passed) {
            val score = (grade.score.getOrElse(0f) * 1000).toLong
            credits += 1
            creditGas += score
          }
        }
        if (credits == 0) BigDecimal(0) else BigDecimal(creditGas) / BigDecimal(credits * 1000)
    }

  }
}

import org.openurp.edu.grade.domain.GpaCalculator.*

class GpaCalculator {

  def calcWms(grades: Iterable[CourseGrade]): BigDecimal = {
    WeightedMean.calcScore(grades)
  }

  def calcAms(grades: Iterable[CourseGrade]): BigDecimal = {
    ArithmeticMean.calcScore(grades)
  }

  def calcGpa(grades: Iterable[CourseGrade]): BigDecimal = {
    WeightedMean.calcGpa(grades)
  }

  def calc(std: Student, grades: Iterable[CourseGrade], statDetail: Boolean, filters: Seq[GradeFilter]): StdGpa = {
    val stdGpa = new StdGpa(std)
    val chain = new GradeFilters.Chain(filters)
    if (statDetail) {
      val gradesMap = Collections.newMap[Semester, mutable.Buffer[CourseGrade]]
      for (grade <- grades) {
        val semesterGrades = gradesMap.getOrElseUpdate(grade.semester, Collections.newBuffer)
        semesterGrades += grade
      }

      val yearGradeMap = Collections.newMap[String, mutable.Buffer[CourseGrade]]
      gradesMap foreach {
        case (semester, semesterGrades) =>
          val stdTermGpa = new StdSemesterGpa()
          stdTermGpa.semester = semester
          stdGpa.add(stdTermGpa)
          val yearGrades = yearGradeMap.getOrElseUpdate(semester.schoolYear, Collections.newBuffer)
          yearGrades ++= semesterGrades

          val filted = chain.filter(semesterGrades)
          stdTermGpa.gpa = this.calcGpa(filted).doubleValue
          stdTermGpa.wms = this.calcWms(filted).doubleValue
          stdTermGpa.ams = this.calcAms(filted).doubleValue
          stdTermGpa.totalCount = semesterGrades.size
          val stats = statCredits(semesterGrades)
          stdTermGpa.totalCredits = stats._1
          stdTermGpa.credits = stats._2
          stdTermGpa.takenCredits = stats._3
      }
      yearGradeMap foreach {
        case (year, yearGrades) =>
          val stdYearGpa = new StdYearGpa()
          stdYearGpa.schoolYear = year
          stdGpa.add(stdYearGpa)
          val filted = chain.filter(yearGrades)
          stdYearGpa.gpa = this.calcGpa(filted).doubleValue
          stdYearGpa.wms = this.calcWms(filted).doubleValue
          stdYearGpa.ams = this.calcAms(filted).doubleValue
          stdYearGpa.totalCount = yearGrades.size
          val stats = statCredits(yearGrades)
          stdYearGpa.totalCredits = stats._1
          stdYearGpa.credits = stats._2
          stdYearGpa.takenCredits = stats._3
      }
    }
    val filted = chain.filter(grades)
    stdGpa.gpa = this.calcGpa(filted).doubleValue
    stdGpa.wms = this.calcWms(filted).doubleValue
    stdGpa.ams = this.calcAms(filted).doubleValue

    val courseMap = Collections.newMap[Course, CourseGrade]
    for (grade <- grades) {
      val add = courseMap.get(grade.course) match {
        case None => true
        case Some(exist) => !exist.passed
      }
      if (add) {
        courseMap.put(grade.course, grade)
      }
    }
    stdGpa.totalCount = courseMap.size
    val totalStats = statCredits(courseMap.values)
    stdGpa.totalCredits = totalStats._1
    stdGpa.credits = totalStats._2
    stdGpa.takenCredits = statTakenCredits(grades)

    stdGpa.updatedAt = Instant.now
    stdGpa
  }

  private def statCredits(grades: Iterable[CourseGrade]): (Float, Float, Float) = {
    var passedCredits = 0f
    var allCredits = 0f
    var takenCredits = 0f
    for (grade <- grades) {
      val level = grade.std.level
      val credits = grade.course.getCredits(level)
      allCredits += credits

      if (grade.passed) passedCredits += credits
      if isTaken(grade) then takenCredits += credits
    }
    (allCredits, passedCredits, takenCredits)
  }

  /** 实修学分和在修学分
   *
   * @param grades
   * @return
   */
  private def statTakenCredits(grades: Iterable[CourseGrade]): Float = {
    var takenCredits = 0f
    for (grade <- grades) {
      val level = grade.std.level
      val credits = grade.course.getCredits(level)
      if isTaken(grade) then takenCredits += credits
    }
    takenCredits
  }

  private def isTaken(grade: CourseGrade): Boolean = {
    !(grade.courseTakeType.id == CourseTakeType.Exemption ||
      grade.gaGrades.size == 1 && grade.hasGaGrade(GradeType.MakeupGa))
  }
}
