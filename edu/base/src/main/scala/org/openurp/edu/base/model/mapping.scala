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
package org.openurp.edu.base.model

import scala.reflect.runtime.universe
import org.beangle.data.model.annotation.code
import org.beangle.data.model.bind.Mapping
import org.openurp.code.BaseCodeBean
import org.beangle.commons.lang.time.WeekState
import org.openurp.edu.base.States

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")
    defaultCache("openurp.edu.base", "read-write")

    bind[Adminclass] on (e => declare(
      e.code is (notnull, length(20), unique),
      e.name is (notnull, length(50)),
      e.grade is (notnull, length(10)),
      e.project & e.department & e.stdType & e.education & e.beginOn are notnull,
      e.stdStates is one2many("adminclass"),
      e.remark is length(100)))

    bind[Classroom] on (e => declare(
      e.project & e.roomType are notnull,
      e.code is (notnull, length(20)),
      e.name is (notnull, length(100))))

    bind[Course] on (e => declare(
      e.code is (notnull, length(32), unique),
      e.name is (notnull, length(222)),
      e.enName is length(300),
      e.project & e.department & e.beginOn & e.updatedAt are notnull,
      e.hours is depends("course"),
      e.remark is length(500)))

    bind[CourseHour].on(e => declare(
      e.course & e.hourType are notnull))

    bind[Direction].on(e => declare(
      e.code is (notnull, length(32), unique),
      e.name is (notnull, length(100)),
      e.enName is length(255),
      e.beginOn & e.updatedAt are notnull,
      e.journals is depends("direction"),
      e.remark is length(200)))

    bind[DirectionJournal] on (e => declare(
      e.direction & e.education & e.depart & e.beginOn are notnull,
      e.remark is (length(200))))

    bind[Major] on (e => declare(
      e.code is (notnull, length(20), unique),
      e.name is (notnull, length(50)),
      e.enName is length(150),
      e.project & e.beginOn & e.updatedAt are notnull,
      e.journals is depends("major"),
      e.directions is depends("major"),
      e.disciplines is depends("major"),
      e.remark is length(100)))

    bind[MajorDiscipline] on (e => declare(
      e.major & e.category & e.beginOn are notnull,
      e.disciplineCode is (notnull, length(50))))

    bind[MajorJournal] on (e => declare(
      e.major & e.duration & e.education & e.depart & e.beginOn are notnull,
      e.remark is length(200)))

    bind[Program] on (e => declare(
      e.grade is (notnull, length(10)),
      e.name is (notnull, length(100)),
      e.department & e.education & e.major & e.duration & e.studyType
        & e.beginOn & e.endOn are notnull,
      e.updatedAt is notnull,
      e.remark is (length(200))))

    bind[Project] on (e => declare(
      e.code is (notnull, length(10), unique),
      e.name is (notnull, length(100)),
      e.school & e.beginOn & e.updatedAt are notnull,
      e.description is length(500)))

    bind[ProjectCode] on (e => declare(
      e.project is notnull,
      e.className is (notnull, length(100)),
      e.codeIds is (notnull, length(2000))))

    bind[Teacher] on (e => declare(
      e.project & e.user & e.beginOn & e.updatedAt are notnull))

    bind[Instructor] on (e => declare(
      e.user is notnull))

    bind[Student] on (e => declare(
      e.code is (notnull, length(30), unique),
      e.person & e.project & e.education & e.state &
        e.enrollOn & e.graduateOn are notnull,
      e.states is depends("std"),
      e.remark is length(200)))

    bind[StudentState] on (e => declare(
      e.std & e.grade & e.department & e.majorDepart & e.major & e.status & e.beginOn are notnull,
      e.remark is length(200)))

    bind[Textbook] on (e => declare(
      e.name is (notnull, length(300)),
      e.isbn is (length(100)),
      e.author is (length(80)),
      e.version is (length(50)),
      e.description is length(300),
      e.beginOn is notnull))
  }
}
