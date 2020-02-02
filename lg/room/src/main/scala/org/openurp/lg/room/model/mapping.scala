/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.lg.room.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    bind[Occupancy] declare { e =>
      e.room is notnull
      e.time.startOn & e.time.beginAt & e.time.endAt & e.time.weekstate are notnull
      e.activityType & e.updatedAt & e.userApp & e.activityId are notnull
      e.comments is length(300)
      index("", false, e.room)
      index("", false, e.activityId)
      index("", false, e.time.startOn)
    }

    bind[UserApp] declare { e =>
      e.name & e.activityUrl are(length(200), notnull)
    }
  }
}
