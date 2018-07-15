/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.course.model

import scala.reflect.runtime.universe
import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.annotation.code
import org.beangle.data.orm.MappingModule
import org.openurp.edu.course.code.model.ClazzTag

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("date")

    //code
    bind[ClazzTag]

    bind[ExamTaker].on(e => declare(
      e.remark is length(100)))

    //course
    bind[CourseTaker].on(e => declare(
      e.remark is length(100)))

    bind[Clazz].on(e => declare(
      e.crn is length(32),
      e.teachers is ordered,
      e.name is length(500),
      e.enrollment.grade is length(20),
      e.exam.beginAt is column("exam_begin_at"),
      e.exam.endAt is column("exam_end_at"),
      e.enrollment.courseTakers & e.enrollment.examTakers & e.enrollment.restrictions &
        e.schedule.sessions are depends("clazz")))

    bind[Restriction].on(e => declare(
      e.items is depends("group"),
      e.children is depends("parent")))

    bind[RestrictionItem]

    //schedule
    bind[Session].on(e => declare(
      e.remark is length(200)))

    bind[ClazzGroup].on(e => declare(
      e.clazzes is one2many("group")))

  }

}
