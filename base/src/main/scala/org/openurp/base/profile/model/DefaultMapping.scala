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

package org.openurp.base.profile.model

import org.beangle.data.orm.{IdGenerator, MappingModule}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[CourseProfile] declare { e =>
      e.description is length(40000)
      e.enDescription is length(40000)

      index("", true, e.course)
    }

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
  }
}
