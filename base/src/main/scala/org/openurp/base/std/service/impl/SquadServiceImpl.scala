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

package org.openurp.base.std.service.impl

import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.std.model.{Squad, Student}
import org.openurp.base.std.service.SquadService

import java.time.Instant

class SquadServiceImpl extends SquadService {

  var entityDao: EntityDao = _

  override def statStdCount(squads: Iterable[Squad]): Int = {
    var updated = 0
    squads foreach { squad =>
      val newCount = squad.stdStates.filter(x => x.std.state.nonEmpty && x.std.state.get.squad == x.squad).map(_.std).distinct.size
      if (newCount != squad.stdCount) {
        squad.stdCount = newCount
        updated += 1
        squad.updatedAt = Instant.now
      }
    }
    entityDao.saveOrUpdate(squads)
    updated
  }

  override def getStudents(squad: Squad): Seq[Student] = {
    val builder = OqlBuilder.from(classOf[Student], "student")
    builder.where("student.state.squad=:squad", squad)
    builder.orderBy("student.code")
    entityDao.search(builder)
  }
}
