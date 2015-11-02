/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.lesson.model

import scala.reflect.runtime.universe

import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.annotation.code
import org.beangle.data.model.bind.Mapping
import org.openurp.edu.lesson.code.model.{ CourseTakeType, ElectionMode, ExamForm, ExamType, LessonTag, TeachLangType }

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("date")

    //code
    bind[ElectionMode]
    bind[ExamType]
    bind[CourseTakeType]
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
