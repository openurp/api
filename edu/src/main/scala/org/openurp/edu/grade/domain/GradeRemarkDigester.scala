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

import org.beangle.commons.lang.Strings
import org.openurp.code.edu.model.{ExamStatus, GradeType}
import org.openurp.edu.grade.model.{CourseGrade, ExamGrade}

object GradeRemarkDigester {
  def digest(grades: Iterable[CourseGrade], hasCourse: Boolean): String = {
    val newGrades = grades.toBuffer.sortBy(_.semester.beginOn)
    val remarkSB = new StringBuilder
    var brString: String = ""
    for (grade <- newGrades) {
      remarkSB.append(brString)
      remarkSB.append(grade.semester.schoolYear).append(grade.semester.name).append(" ")
      if (hasCourse) {
        remarkSB.append(grade.course.name).append(" ")
      }
      if (grade.passed) {
        append(remarkSB, grade.scoreText)
      } else {
        val ga = grade.getGaGrade(new GradeType(GradeType.EndGa)).orNull
        remarkSB.append("期末")
        if (null == ga || ga.scoreText.isEmpty) {
          remarkSB.append(disgetExamGrade(grade.getExamGrade(new GradeType(GradeType.End)).orNull))
        } else {
          append(remarkSB, ga.scoreText)
        }
        val delay = grade.getGaGrade(new GradeType(GradeType.DelayGa)).orNull
        if (null == delay) {
          remarkSB.append("  补考")
          val makeup = grade.getGaGrade(new GradeType(GradeType.MakeupGa)).orNull
          if (null == makeup || makeup.scoreText.isEmpty) {
            remarkSB.append(disgetExamGrade(grade.getExamGrade(new GradeType(GradeType.Makeup)).orNull))
          } else {
            append(remarkSB, makeup.scoreText)
          }
        } else {
          remarkSB.append("  缓考")
          if (delay.scoreText.isEmpty) {
            remarkSB.append(disgetExamGrade(grade.getExamGrade(new GradeType(GradeType.Delay)).orNull))
          } else {
            append(remarkSB, delay.scoreText)
          }
        }
      }
      brString = "\n"
    }
    remarkSB.toString
  }

  private def append(sb: StringBuilder, str: Option[String]): Unit = {
    str foreach { s => sb.append(s) }
  }

  def disgetExamGrade(eg: ExamGrade): String = {
    if (null == eg) {
      "--"
    } else {
      if (Strings.isNotBlank(eg.scoreText.orNull) && eg.examStatus.id == ExamStatus.Normal) {
        eg.scoreText.getOrElse("")
      } else {
        eg.examStatus.name
      }
    }
  }
}
