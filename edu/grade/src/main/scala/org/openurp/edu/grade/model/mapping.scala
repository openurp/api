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
package org.openurp.edu.grade.model

import org.beangle.data.model.annotation.config
import org.beangle.data.orm.MappingModule
import org.openurp.code.edu.model.GradeType
import org.openurp.edu.grade.course.model.CourseGrade
import org.openurp.edu.grade.course.model.CourseGradeState
import org.openurp.edu.grade.course.model.ExamGrade
import org.openurp.edu.grade.course.model.ExamGradeState
import org.openurp.edu.grade.course.model.GaGrade
import org.openurp.edu.grade.course.model.GaGradeState
import org.openurp.edu.grade.course.model.StdGpa
import org.openurp.edu.grade.course.model.StdSemesterGpa
import org.openurp.edu.grade.course.model.StdYearGpa
import org.openurp.edu.grade.moral.model.MoralGrade

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    //code
    bind[GradeType]

    //grade
    bind[CourseGrade].on(e => declare(
      e.crn is length(20),
      e.course & e.courseTakeType & e.project & e.courseType & e.semester & e.gradingMode are notnull,
      e.operator is length(100),
      e.scoreText is length(5),
      e.remark is length(200),
      e.examGrades & e.gaGrades are depends("courseGrade")))

    bind[AbstractGradeState].on(e => declare(
      e.gradingMode & e.beginOn are notnull,
      e.operator is length(100)))

    bind[CourseGradeState].on(e => declare(
      e.clazz is notnull,
      e.examStates is depends("courseGradeState"),
      e.gaStates is depends("courseGradeState")))

    bind[Grade].on(e => declare(
      e.gradingMode is notnull,
      e.scoreText is length(5),
      e.operator is length(100)))

    bind[ExamGrade].on(e => declare(
      e.gradeType & e.examStatus & e.courseGrade are notnull))

    bind[ExamGradeState].on(e => declare(
      e.gradeState & e.gradeType are notnull))

    bind[GaGrade].on(e => declare(
      e.gradeType & e.courseGrade is notnull))

    bind[GaGradeState].on(e => declare(
      e.gradeState is notnull,
      e.remark is length(50)))

    bind[StdGpa].on(e => declare(
      e.project & e.std & e.updatedAt are notnull,
      e.semesterGpas is depends("stdGpa"),
      e.yearGpas is depends("stdGpa")))

    bind[StdSemesterGpa].on(e => declare(
      e.semester & e.stdGpa are notnull))

    bind[StdYearGpa].on(e => declare(
      e.schoolYear & e.stdGpa are notnull))

    bind[GradeRateConfig].on(e => declare(
      e.gradingMode is notnull,
      e.items is depends("config")))

    bind[GradeRateItem].on(e => declare(
      e.config is notnull))

    bind[MoralGrade].on(e => declare(
      e.std & e.semester & e.gradingMode are notnull,
      e.scoreText is length(5),
      e.operator is length(100)))

  }

}
