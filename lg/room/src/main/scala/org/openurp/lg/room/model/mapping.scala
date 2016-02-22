/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.lg.room.model

import scala.reflect.runtime.universe
import org.beangle.data.model.bind.Mapping
import org.openurp.lg.room.occupancy.model.Occupancy

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    bind[Occupancy] on (e => declare(
      e.room is notnull,
      e.time.startOn & e.time.weekday & e.time.beginAt & e.time.endAt & e.time.weekstate are notnull,
      e.usage & e.updatedAt are notnull,
      e.userid is length(100),
      e.comments is length(300)))

  }
}
