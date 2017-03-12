/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
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
package org.openurp.edu.lesson.model

import scala.reflect.runtime.universe
import org.beangle.commons.lang.annotation.beta
import org.beangle.commons.model.annotation.code
import org.beangle.commons.model.bind.Mapping
import org.openurp.edu.lesson.code.model.LessonTag

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("date")

    //code
    bind[LessonTag]

    bind[ExamTaker].on(e => declare(
      e.remark is length(100)))

    //lesson
    bind[CourseTaker].on(e => declare(
      e.remark is length(100)))

    bind[Lesson].on(e => declare(
      e.no is length(32),
      e.teachers is ordered,
      e.teachclass.name is (length(500), column("class_name")),
      e.teachclass.grade is length(20),
      e.exam.beginAt is column("exam_begin_at"),
      e.exam.endAt is column("exam_end_at"),
      e.teachclass.courseTakers & e.teachclass.examTakers & e.teachclass.groups &
        e.schedule.activities are depends("lesson")))

    bind[LessonLimitGroup].on(e => declare(
      e.items is depends("group"),
      e.children is depends("parent")))

    bind[LessonLimitItem]

    //schedule
    bind[CourseActivity].on(e => declare(
      e.remark is length(200)))

    bind[LessonGroup].on(e => declare(
      e.lessons is one2many("group")))

  }

}
