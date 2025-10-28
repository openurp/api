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

package org.openurp.base.resource.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultCache("openurp-base", "read-write")

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

    bind[Classroom] declare { e =>
      e.code is length(20)
      e.name is length(100)
      e.roomNo is length(20)
      e.devices is one2many("room")
      index("", true, e.school, e.code)
    }

    bind[Laboratory] declare { e =>
      e.code is length(20)
      e.name is length(100)
      index("", true, e.school, e.code)
    }

    bind[Device] declare { e =>
      e.name is length(40)
      e.remark is length(100)
      e.ip is length(40)
    }
  }
}
