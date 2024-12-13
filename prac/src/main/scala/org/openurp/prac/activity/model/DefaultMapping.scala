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

package org.openurp.prac.activity.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[StdPracticeInfo] declare { e =>
      e.place is length(100)
      e.remark is length(100)
    }
    bind[StdPracticeHour] declare { e =>
      index("", true, e.std, e.category)
    }

    bind[PracClazz].declare { e =>
      e.schedules is depends(classOf[PracClazzSchedule], "activity")
    }
    bind[PracClazzSchedule]

    bind[PracActivity].declare { e =>
      e.schedules is depends(classOf[PracActivitySchedule], "activity")
    }
    bind[PracActivitySchedule]
  }
}
