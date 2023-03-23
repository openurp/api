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

package org.openurp.base.edu.model

import org.beangle.data.orm.{IdGenerator, MappingModule}

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

    bind[Classroom] declare { e =>
      e.code is length(20)
      e.name is length(100)
      e.roomNo is length(20)
      index("", true, e.code)
    }

    bind[CourseCluster] declare { e =>
      e.name is length(100)
      e.code is length(20)
      index("", true, e.project, e.code)
    }

    bind[Course] declare { e =>
      e.code is length(32)
      e.name is length(222)
      e.enName is length(300)
      e.hours is depends("course")
      e.remark is length(500)
      e.levels is depends("course")
      e.prerequisites is(joinColumn("course_id"), eleColumn("prerequisite_id"))
      index("", true, e.project, e.code)
      index("", false, e.code)
    }

    bind[CourseHour].declare { e =>
      index("", false, e.course)
      index("", true, e.course, e.teachingNature)
    }

    bind[CourseLevel] declare { e =>
      index("", true, e.course, e.level)
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

    bind[Teacher].declare { e =>
      e.tqcNumber is length(20)
      e.oqc is length(200)
      e.name is length(100)
      index("", true, e.staff)
      index("", true, e.user)
    }.generator(IdGenerator.Assigned)

    bind[Textbook] declare { e =>
      e.name is length(300)
      e.isbn is length(100)
      e.author is length(80)
      e.edition is length(50)
      e.description is length(300)
    }

    bind[TeachingOffice] declare { e =>
      index("", true, e.project, e.code)
    }

    all.cacheAll()
  }
}
