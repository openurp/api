/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.base.edu.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultCache("openurp.base", "read-write")

    bind[TimeSetting] declare { e =>
      e.name is length(20)
      e.units is depends("setting")
    }

    bind[CourseUnit] declare { e =>
      e.enName is length(30)
      e.name & e.indexno are length(20)
    }

    bind[Calendar] declare { e =>
      e.semesters is depends("calendar")
      e.code is length(10)
      e.name is length(80)
      index("", true, e.school, e.code)
    }

    bind[CalendarStage] declare { e =>
      e.name is length(100)
    }

    bind[Semester].declare { e =>
      e.code is length(15)
      e.name & e.schoolYear are length(10)
      e.stages is depends("semester")
      index("", true, e.calendar, e.code)
    }.generator("code")

    bind[SemesterStage] declare { e =>
      e.remark is length(500)
    }

    bind[Squad] declare { e =>
      e.code is length(20)
      e.name is length(100)
      e.grade is length(10)
      e.stdStates is one2many("squad")
      e.remark is length(100)
      index("", true, e.project, e.code)
      index("", false, e.code)
    }

    bind[Classroom] declare { e =>
      e.code is length(20)
      e.name is length(100)
      e.roomNo is length(20)
      index("", true, e.code)
    }

    bind[Course] declare { e =>
      e.code is length(32)
      e.name is length(222)
      e.enName is length(300)
      e.hours is depends("course")
      e.remark is length(500)
      index("", true, e.project, e.code)
      index("", false, e.code)
    }

    bind[CourseHour].declare { e =>
      index("", false, e.course)
    }

    bind[Direction].declare { e =>
      e.code is length(32)
      e.name is length(100)
      e.enName is length(255)
      e.journals is depends("direction")
      e.remark is length(200)
      index("", true, e.project, e.code)
      index("", false, e.major)
    }

    bind[DirectionJournal] declare { e =>
      e.remark is length(200)
      index("", false, e.direction)
    }

    bind[Major] declare { e =>
      e.code is length(20)
      e.name is length(50)
      e.enName is length(150)
      e.journals is depends("major")
      e.directions is depends("major")
      e.disciplines is depends("major")
      e.schoolLengths is depends("major")
      e.remark is length(100)
      index("", true, e.project, e.code)
    }

    bind[MajorDiscipline] declare { e =>
      e.disciplineCode is length(50)
      index("", false, e.major)
    }

    bind[MajorJournal] declare { e =>
      e.remark is length(200)
      index("", false, e.major)
    }

    bind[SchoolLength] declare { e =>
      e.fromGrade & e.toGrade are length(10)
    }

    bind[Project] declare { e =>
      e.code.is(length(10), unique)
      e.name is length(100)
      e.departments is ordered
      e.description is length(500)
    }

    bind[ProjectCode] declare { e =>
      e.className is length(100)
      e.codeIds is length(2000)
    }

    bind[Teacher] declare { e =>
      index("", true, e.user)
    }

    bind[Student] declare { e =>
      e.states is depends("std")
      e.remark is length(200)

      index("", true, e.user, e.project)
      index("", false, e.user)
      index("", false, e.state)
      index("", false, e.project)
    }

    bind[StudentState] declare { e =>
      e.remark is length(200)
      index("", false, e.std)
      index("", false, e.department)
      index("", false, e.major)
      index("", false, e.squad)
    }

    bind[Textbook] declare { e =>
      e.name is length(300)
      e.isbn is length(100)
      e.author is length(80)
      e.edition is length(50)
      e.description is length(300)
    }

    bind[TeachingGroup] declare { e =>
      index("", true, e.project, e.code)
    }

    bind[ExternStudent]

    all.except(classOf[Student], classOf[StudentState], classOf[ExternStudent]).cacheAll()
  }
}
