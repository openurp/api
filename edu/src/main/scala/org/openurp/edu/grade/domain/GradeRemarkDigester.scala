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
import org.openurp.base.edu.model.Course
import org.openurp.code.edu.model.{ExamStatus, GradeType}
import org.openurp.edu.grade.model.{CourseGrade, ExamGrade, Grade}

object GradeRemarkDigester {
  def digest(course: Course, grades: Iterable[CourseGrade], hasCourse: Boolean): String = {
    if (course.subCourse.nonEmpty && course.terms > 0) {
      ""
    } else {
      val newGrades = grades.toBuffer.sortBy(_.semester.beginOn)
      val remarkSB = new StringBuilder
      var brString: String = ""
      for (grade <- newGrades) {
        remarkSB.append(brString)
        remarkSB.append(grade.semester.schoolYear).append(grade.semester.name).append(" ")
        if (hasCourse) {
          remarkSB.append(course.name).append(" ")
        }
        if (grade.passed) {
          grade.scoreText foreach { s => remarkSB.append(s) }
        } else {
          appendGaAndExam(remarkSB, grade, "期末", GradeType.EndGa, GradeType.End)
          val delay = grade.getGaGrade(new GradeType(GradeType.DelayGa)).orNull
          if (null == delay) {
            appendGaAndExam(remarkSB, grade, " 补考", GradeType.MakeupGa, GradeType.Makeup)
          } else {
            appendGaAndExam(remarkSB, grade, " 缓考", GradeType.DelayGa, GradeType.Delay)
          }
        }
        brString = "\n"
      }
      remarkSB.toString
    }
  }

  private def appendGaAndExam(sb: StringBuilder, grade: CourseGrade, name: String, ggTypeId: Int, egTypeId: Int): Unit = {
    val ga = grade.getGaGrade(new GradeType(ggTypeId)).orNull
    sb.append(name)
    if (null == ga || ga.scoreText.isEmpty) {
      sb.append(disgetExamGrade(grade.getExamGrade(new GradeType(egTypeId)).orNull))
    } else {
      if (ga.status == Grade.Status.Published) {
        ga.scoreText foreach { s => sb.append(s) }
      } else {
        sb.append("未发布")
      }
    }
  }

  def disgetExamGrade(eg: ExamGrade): String = {
    if (null == eg) {
      "--"
    } else {
      if (eg.status == Grade.Status.Published) {
        if (Strings.isNotBlank(eg.scoreText.orNull) && eg.examStatus.id == ExamStatus.Normal) {
          eg.scoreText.getOrElse("")
        } else {
          eg.examStatus.name
        }
      } else {
        "未发布"
      }
    }
  }
}
