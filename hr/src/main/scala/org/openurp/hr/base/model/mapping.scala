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

package org.openurp.hr.base.model

import org.beangle.data.model.annotation.code
import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[DutyInfo]

    bind[EducationInfo].declare { e =>
      e.certificateNo is length(50)
      e.major is length(100)
      e.school is length(200)
    }

    bind[TeacherProfile] declare { e =>
      e.intro is length(40000)
      e.harvest is length(40000)
      e.teachingCareer is length(1000)
      e.titles is length(1000)

      index("", true, e.teacher)
    }

    bind[Staff].declare { e =>
      e.states is (depends("staff"))
    }

    bind[StaffState]
    bind[TitleInfo]
    bind[TutorInfo]
    bind[WorkInfo]
  }
}
