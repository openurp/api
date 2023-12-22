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

package org.openurp.edu.attendance.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {

    bind[Attendance] declare { e =>
      index("", true, e.semester, e.clazz, e.std)
    }

    bind[StdLeave] declare { e =>
      e.reason is length(200)
      e.files is depends("leave")
    }

    bind[StdLeaveLesson] declare { e =>
      e.lessonTime is length(11)
      index("", false, e.clazz)
    }

    bind[StdLeaveFile]
  }
}
