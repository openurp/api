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

package org.openurp.base.hr.model

import org.beangle.data.orm.{IdGenerator, MappingModule}

class DefaultMapping extends MappingModule {
  def binding(): Unit = {
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

    bind[StaffTitle]

    bind[Official] declare { e =>
      e.duty is length(100)
    }

    bind[Secretary] declare { e =>
      e.officeAddr is length(100)
      e.officePhone is length(50)
      e.officeEmail is length(100)
    }

    bind[Mentor].declare { e =>
      index("", true, e.staff)
    }.generator(IdGenerator.Assigned)

    bind[Teacher].declare { e =>
      e.tqcNumber is length(20)
      e.oqc is length(200)
      e.name is length(100)
      index("", true, e.staff)
    }.generator(IdGenerator.Assigned)

    bind[StaffProfile] declare { e =>
      e.intro is length(60000)
      e.harvest is length(60000)
      e.career is length(4000)
      e.titles is length(1000)

      e.projects is length(1000)
      e.courses is length(1000)
      e.awards is length(1000)
      index("", true, e.staff)
    }

    bind[President] declare { e =>
      e.name is length(50)
      e.enName is length(100)
    }

    bind[TutorJournal]

    bind[TutorMajor]
  }
}
