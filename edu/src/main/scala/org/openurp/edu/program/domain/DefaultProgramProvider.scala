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
package org.openurp.edu.program.domain

import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.edu.model.{Student, StudentState}
import org.openurp.edu.program.model.Program

class DefaultProgramProvider extends ProgramProvider {
  var entityDao: EntityDao = _

  override def getProgram(std: Student): Option[Program] = {
    std.state match {
      case Some(s) => getProgram(s)
      case None => None
    }
  }

  override def getProgram(state: StudentState): Option[Program] = {
    val query = OqlBuilder.from(classOf[Program], "p")
    query.where("p.grade=:grade", state.grade)
    query.where("p.department=:department", state.department)
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
