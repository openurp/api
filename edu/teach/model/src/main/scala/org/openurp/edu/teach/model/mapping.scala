/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.teach.model

import scala.reflect.runtime.universe

import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.annotation.code
import org.beangle.data.model.bind.Mapping
import org.openurp.edu.teach.code.model.{ CourseTakeType, ElectionMode, ExamForm, ExamType, GradeType, LessonTag, TeachLangType }
import org.openurp.edu.teach.exam.model.{ ExamActivity, ExamMonitor, ExamRoom, ExamTake }
import org.openurp.edu.teach.grade.course.model.{ CourseGrade, CourseGradeState, ExamGrade, ExamGradeState, GaGrade, GaGradeState, StdGpa, StdSemesterGpa, StdYearGpa }
import org.openurp.edu.teach.grade.exchange.model.{ ExchangeCourse, ExchangeSchool }
import org.openurp.edu.teach.grade.model.{ AbstractGradeState, Grade, GradeRateConfig, GradeRateItem }
import org.openurp.edu.teach.grade.moral.model.MoralGrade
import org.openurp.edu.teach.lesson.model.{ CourseTake, Lesson, LessonLimitGroup, LessonLimitItem }
import org.openurp.edu.teach.schedule.model.{ CourseActivity, LessonGroup }

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("date")

    //code
    bind[ElectionMode]
    bind[ExamType]
    bind[CourseTakeType]
    bind[GradeType]
    bind[LessonTag]
    bind[TeachLangType]
    bind[ExamForm]

    //exam
    bind[ExamActivity].on(e => declare(
      e.lesson & e.examOn & e.examType & e.beginAt & e.endAt & e.semester are notnull,
      e.remark is length(100))).generator("date")

    bind[ExamMonitor].on(e => declare(
      e.examRoom & e.department is notnull))

    bind[ExamRoom].on(e => declare(
      e.classroom & e.semester are notnull,
      e.monitors is depends("examRoom")))

    bind[ExamTake].on(e => declare(
      e.lesson & e.std & e.examType & e.examStatus are notnull,
      e.remark is length(100)))

    //grade
    bind[CourseGrade].on(e => declare(
      e.lessonNo is length(20),
      e.course & e.courseTakeType & e.project & e.courseType & e.semester & e.markStyle are notnull,
      e.operator is length(100),
      e.scoreText is length(5),
      e.remark is length(200),
      e.examGrades & e.gaGrades are depends("courseGrade")))

    bind[AbstractGradeState].on(e => declare(
      e.markStyle & e.beginOn are notnull,
      e.operator is length(100)))

    bind[CourseGradeState].on(e => declare(
      e.lesson is notnull,
      e.examStates is depends("courseGradeState"),
      e.gaStates is depends("courseGradeState")))

    bind[Grade].on(e => declare(
      e.markStyle is notnull,
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

    bind[ExchangeCourse].on(e => declare(
      e.school is notnull,
      e.remark is length(100)))
    bind[ExchangeSchool].on(e => declare(
      e.code & e.name & e.beginOn & e.country & e.updatedAt are notnull))

    bind[GradeRateConfig].on(e => declare(
      e.scoreMarkStyle is notnull,
      e.items is depends("config")))

    bind[GradeRateItem].on(e => declare(
      e.config is notnull))

    bind[MoralGrade].on(e => declare(
      e.std & e.semester & e.markStyle are notnull,
      e.scoreText is length(5),
      e.operator is length(100)))

    //lesson
    bind[CourseTake].on(e => declare(
      e.lesson & e.course & e.std & e.takeType & e.electionMode & e.semester & e.updatedAt are notnull,
      e.remark is length(100)))

    bind[Lesson].on(e => declare(
      e.no is length(32),
      e.project & e.course & e.courseType & e.teachDepart & e.semester & e.langType are notnull,
      e.teachers is ordered,
      e.teachclass.name is length(500),
      e.teachclass.courseTakes & e.teachclass.examTakes & e.teachclass.groups &
        e.schedule.activities & e.exam.activities are depends("lesson"),
      e.state & e.updatedAt & e.schedule.publishState are notnull))

    bind[LessonLimitGroup].on(e => declare(
      e.lesson is notnull,
      e.items is depends("group"),
      e.children is depends("parent")))

    bind[LessonLimitItem].on(e => declare(
      e.meta & e.group & e.content are notnull))

    //schedule
    bind[CourseActivity].on(e => declare(
      e.lesson & e.time.startOn & e.time.weekday & e.time.beginAt & e.time.endAt & e.time.weekstate are notnull,
      e.remark is length(200)))

    bind[LessonGroup].on(e => declare(
      e.lessons is one2many("group"),
      e.name & e.teachDepart & e.semester are notnull))

  }

}
