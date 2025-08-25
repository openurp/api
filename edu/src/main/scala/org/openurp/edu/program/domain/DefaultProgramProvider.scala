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

package org.openurp.edu.program.domain

import org.beangle.commons.collection.Collections
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.model.Department
import org.openurp.base.std.model.{Student, StudentState}
import org.openurp.edu.program.model.{Program, StdProgramBinding}

class DefaultProgramProvider extends ProgramProvider {
  var entityDao: EntityDao = _

  override def getProgram(std: Student): Option[Program] = {
    if (std.persisted) {
      entityDao.findBy(classOf[StdProgramBinding], "std", std).headOption match {
        case None =>
          std.state match {
            case Some(s) => getProgram(s)
            case None => None
          }
        case Some(binding) => Some(binding.program)
      }
    } else {
      std.state match {
        case Some(s) => getProgram(s)
        case None => None
      }
    }
  }

  override def getProgram(state: StudentState): Option[Program] = {
    val departs = Collections.newSet[Department]
    var depart = state.department
    while (null != depart && !departs.contains(depart)) {
      departs += depart
      depart = depart.parent.orNull
    }

    val query = OqlBuilder.from(classOf[Program], "p")
    query.where("p.grade=:grade", state.grade)
    query.where("p.department in (:departments)", departs)
    query.where("p.major=:major", state.major)
    query.where("p.level=:level", state.std.level)
    val ps = entityDao.search(query)

    state.direction match {
      case Some(d) =>
        val firstTry = ps.filter(_.direction.contains(d)).find(DefaultProgramMatcher.isMatched(_, state))
        firstTry match {
          case rs@Some(_) => rs
          case None => ps.filter(_.direction.isEmpty).find(DefaultProgramMatcher.isMatched(_, state))
        }
      case None =>
        ps.filter(_.direction.isEmpty).find(DefaultProgramMatcher.isMatched(_, state))
    }
  }
}
