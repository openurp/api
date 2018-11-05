/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright (c) 2005, The OpenURP Software.
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
package org.openurp.edu.base.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")
    defaultCache("openurp.edu.base", "read-write")

    bind[TimeSetting] on (e => declare(
      e.name is length(20),
      e.units is (depends("setting"))))

    bind[CourseUnit] on (e => declare(
      e.enName is length(30),
      e.name & e.indexno are length(20)))

    bind[Calendar] on (e => declare(
      e.semesters is (depends("calendar")),
      e.code is length(10),
      e.name is length(80)))

    bind[Semester].on(e => declare(
      e.code is (length(15)),
      e.name & e.schoolYear are (length(10)))).generator("code")

    bind[Holiday] on (e => declare(
      e.name is length(20),
      e.endOn is notnull))

    bind[Squad] on (e => declare(
      e.code is (length(20), unique),
      e.name is length(50),
      e.grade is length(10),
      e.stdStates is one2many("squad"),
      e.remark is length(100)))

    bind[Classroom] on (e => declare(
      e.code is length(20),
      e.name is length(100)))

    bind[Course] on (e => declare(
      e.code is (length(32), unique),
      e.name is length(222),
      e.enName is length(300),
      e.hours is depends("course"),
      e.remark is length(500)))

    bind[CourseHour].on(e => declare(
      e.course & e.hourType are notnull))

    bind[Direction].on(e => declare(
      e.code is (length(32), unique),
      e.name is length(100),
      e.enName is length(255),
      e.journals is depends("direction"),
      e.remark is length(200)))

    bind[DirectionJournal] on (e => declare(
      e.remark is length(200)))

    bind[Major] on (e => declare(
      e.code is (length(20), unique),
      e.name is length(50),
      e.enName is length(150),
      e.journals is depends("major"),
      e.directions is depends("major"),
      e.disciplines is depends("major"),
      e.remark is length(100)))

    bind[MajorDiscipline] on (e => declare(
      e.disciplineCode is length(50)))

    bind[MajorJournal] on (e => declare(
      e.remark is length(200)))

    bind[Program] on (e => declare(
      e.grade is length(10),
      e.name is length(100),
      e.termCampuses is depends("program"),
      e.remark is length(200)))

    bind[Project] on (e => declare(
      e.code is (length(10), unique),
      e.name is length(100),
      e.departments is ordered,
      e.description is length(500)))

    bind[ProjectCode] on (e => declare(
      e.className is length(100),
      e.codeIds is length(2000)))

    bind[Teacher]

    bind[Instructor]

    bind[Student] on (e => declare(
      e.code is (length(30), unique),
      e.states is depends("std"),
      e.remark is length(200)))

    bind[StudentState] on (e => declare(
      e.remark is length(200)))

    bind[Textbook] on (e => declare(
      e.name is length(300),
      e.isbn is length(100),
      e.author is length(80),
      e.version is length(50),
      e.description is length(300)))

    bind[TermCampus]

    all.except(classOf[Student], classOf[StudentState]).cacheable()
  }
}
