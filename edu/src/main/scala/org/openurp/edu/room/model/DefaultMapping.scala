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

package org.openurp.edu.room.model

import org.beangle.data.orm.MappingModule
import org.openurp.edu.room.config.{RoomApplyDepartScope, RoomApplySetting}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[Occupancy] declare { e =>
      e.comments is length(400)
      index("", false, e.room)
      index("", false, e.activityId)
      index("", false, e.time.startOn)
    }

    bind[RoomOccupyApp] declare { e =>
      e.name & e.activityUrl are length(200)
    }

    bind[RoomApply] declare { e =>
      e.activity.name is column("activity_name")
      e.space.roomComment is length(1200)
      e.applicant.user is column("applicant_id")
    }

    bind[RoomAvailableTime]
  }
}
