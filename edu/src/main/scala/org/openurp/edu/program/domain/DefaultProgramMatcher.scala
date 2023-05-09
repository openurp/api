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
import org.openurp.base.model.Department
import org.openurp.base.std.model.{Squad, StudentState}
import org.openurp.edu.program.model.Program

object DefaultProgramMatcher extends ProgramMatcher {

  override def isMatched(program: Program, state: StudentState): Boolean = {
    val std = state.std
    if (program.grade != state.grade ||
      !departMatched(program.department, state.department) ||
      program.major != state.major ||
      program.level != std.level) {
      false
    } else {
      var matched = true
      program.campus foreach { pc => matched = pc == state.campus }
      if (matched) {
        program.direction foreach { pd => matched = pd == state.direction.orNull }
      }
      if matched then matched = program.stdTypes.isEmpty || program.stdTypes.contains(std.stdType)
      matched
    }
  }

  override def isMatched(program: Program, squad: Squad): Boolean = {
    if (program.grade != squad.grade ||
      !departMatched(program.department, squad.department) ||
      program.level != squad.level) {
      false
    } else {
      var matched = program.major == squad.major.orNull
      if (matched) {
        program.campus foreach { pc => matched = pc == squad.campus }
      }
      if (matched) {
        program.direction foreach { pd => matched = pd == squad.direction.orNull }
      }
      if matched && squad.stdType.nonEmpty then
        matched = program.stdTypes.isEmpty || program.stdTypes.contains(squad.stdType.get)
      matched
    }
  }

  def departMatched(programDepart: Department, userDepart: Department): Boolean = {
    if programDepart == userDepart then
      true
    else
      val departs = Collections.newSet[Department]
      var depart = userDepart
      while (null != depart && !departs.contains(depart)) {
        departs += depart
        depart = depart.parent.orNull
      }
      departs.contains(programDepart)
  }
}
