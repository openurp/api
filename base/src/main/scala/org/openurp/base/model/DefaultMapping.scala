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

package org.openurp.base.model

import org.beangle.data.orm.{IdGenerator, MappingModule}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultCache("openurp.base", "read-write")

    bind[School] declare { e =>
      e.code.is(length(10), unique)
      e.name is length(50)
    }

    bind[Department] declare { e =>
      e.code is length(10)
      e.name is length(80)
      e.enName is length(100)
      e.shortName is length(100)
      e.indexno is length(20)
      e.children is depends("parent")
      e.remark is length(200)
      index("", true, e.school, e.code)
    }

    bind[Campus] declare { e =>
      e.code is length(10)
      e.name is length(80)
      e.enName & e.shortName are (length(100))
      e.remark is length(200)
      index("", true, e.school, e.code)
    }

    bind[Building] declare { e =>
      e.code is length(10)
      e.name is length(80)
      e.enName & e.shortName are (length(100))
      e.remark is length(200)

      index("", true, e.school, e.code)
    }

    bind[Room] declare { e =>
      e.code is length(10)
      e.name is length(80)
      e.remark is length(200)

      index("", true, e.school, e.code)
    }

    bind[User] declare { e =>
      e.code is length(30)
      e.name is length(80)
      e.email is length(80)
      e.mobile is length(15)
      e.remark is length(200)
      index("", true, e.school, e.code)
    }

    bind[Person].declare { e =>
      e.code.is(unique, length(30))
      e.name.familyName & e.name.givenName are length(80)
      e.name.formatedName is length(100)
      e.name.middleName is length(50)
      e.formerName & e.phoneticName are length(100)
    }

    bind[Version].declare { e =>
      e.version is length(20)
      e.description is length(200)
    }

    bind[ExternSchool].generator(IdGenerator.AutoIncrement)

    bind[Project] declare { e =>
      e.code.is(length(10), unique)
      e.name is length(100)
      e.description is length(500)
    }

    bind[ProjectProperty] declare { e =>
      e.typeName is length(100)
      e.description is length(200)
      e.value is(length(2000), column("value_"))

      index("", true, e.project, e.name)
    }

    bind[ProjectCode] declare { e =>
      e.className is length(100)
      e.codeIds is length(2000)
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

    bind[Staff].declare { e =>
      e.idNumber is length(18)
      e.mobile is length(20)
      e.email is length(100)
      e.homepage is length(200)
      e.organization is length(200)
      e.name is length(100)
      e.code is length(20)
      e.external is column("external_")
      index("", true, e.school, e.code)
    }

    all.except(classOf[User], classOf[Person]).cacheable()
  }
}
