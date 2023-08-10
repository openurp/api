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

import org.beangle.data.dao.OqlBuilder
import org.openurp.base.model.Person
import org.openurp.base.service.AbstractBaseService
import org.openurp.base.std.model.{Graduate, Student, StudentState}
import org.openurp.base.std.service.StudentService
import org.openurp.code.std.model.StudentStatus

import java.time.LocalDate

class StudentServiceImpl extends AbstractBaseService, StudentService {

  override def graduate(std: Student, endOn: LocalDate, graduated: StudentStatus): Unit = {
    val inschool = entityDao.get(classOf[StudentStatus], 1)
    val graduateState =
      std.states.filter(x => x.beginOn == x.endOn).sortBy(_.endOn).lastOption.getOrElse {
        val state = std.state.get
        val previousDay = endOn.minusDays(1L)
        state.endOn = previousDay
        if (state.status == graduated) {
          state.status = inschool
          state.inschool = true
        }
        entityDao.saveOrUpdate(state)
        val newState = new StudentState
        newState.std = state.std
        newState.grade = state.grade
        newState.department = state.department
        newState.campus = state.campus
        newState.major = state.major
        newState.direction = state.direction
        newState.squad = state.squad
        newState
      }
    graduateState.inschool = false
    graduateState.status = graduated
    graduateState.beginOn = endOn
    graduateState.endOn = endOn
    std.state = Option(graduateState)
    std.endOn = endOn
    entityDao.saveOrUpdate(graduateState, std)
  }

}
