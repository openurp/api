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

package org.openurp.base.std.model

import org.beangle.data.orm.{IdGenerator, MappingModule}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultCache("openurp.base", "read-write")

    bind[Squad] declare { e =>
      e.code is length(20)
      e.name is length(100)
      e.enName is length(250)
      e.grade is length(10)
      e.stdStates is one2many("squad")
      e.remark is length(100)
      index("", true, e.project, e.code)
      index("", false, e.code)
    }

    bind[StudentTutor]

    bind[Student] declare { e =>
      e.code is length(15)
      e.name is length(100)
      e.states is(depends("std"), orderby("endOn"))
      e.tutors is depends("std")
      e.remark is length(200)

      index("", true, e.code, e.project)
      index("", false, e.code)
      index("", false, e.state)
      index("", false, e.project)
      index("", false, e.person)
      index("", false, e.user)
    }

    bind[StudentState] declare { e =>
      e.remark is length(200)
      index("", false, e.std)
      index("", false, e.department)
      index("", false, e.major)
      index("", false, e.squad)
    }

    bind[ExternStudent]

    bind[GraduateSeason].declare { e =>
      index("", true, e.project, e.code)
    }.generator(IdGenerator.Assigned)

    bind[Graduate] declare { e =>
      e.certificateNo is length(100)
      e.certificateSeqNo is length(50)
      e.diplomaNo is length(100)
      index("", true, e.std)
    }

    bind[Grade].declare { e =>
      index("", true, e.project, e.code)
    }.generator(IdGenerator.Assigned)

    bind[Thesis] declare { e =>
      e.title is length(500)
      e.comments is length(500)
      e.keywords is length(200)
      e.researchField is length(200)
      e.scoreText is length(20)
      index("", true, e.std)
    }
    all.except(classOf[Student], classOf[StudentState], classOf[ExternStudent], classOf[Graduate]).cacheAll()
  }
}
