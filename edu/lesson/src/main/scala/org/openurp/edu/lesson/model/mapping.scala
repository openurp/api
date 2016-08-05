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
import org.openurp.edu.lesson.code.model.LessonTag

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("date")

    //code
    bind[LessonTag]

    //exam
    bind[ExamActivity].on(e => declare(
      e.remark is length(100))).generator("date")

    bind[ExamMonitor]
    bind[ExamRoom].on(e => declare(
      e.monitors is depends("examRoom")))

    bind[ExamTake].on(e => declare(
      e.remark is length(100)))

    //lesson
    bind[CourseTake].on(e => declare(
      e.remark is length(100)))

    bind[Lesson].on(e => declare(
      e.no is length(32),
      e.teachers is ordered,
      e.teachclass.name is (length(500), column("class_name")),
      e.schedule.classroomType is (column("room_type_id")),
      e.teachclass.courseTakes & e.teachclass.examTakes & e.teachclass.groups &
        e.schedule.activities & e.exam.activities are depends("lesson")))

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
