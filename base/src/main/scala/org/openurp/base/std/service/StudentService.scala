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

package org.openurp.base.std.service

import org.beangle.data.dao.OqlBuilder
import org.openurp.base.model.Person
import org.openurp.base.std.model.{Graduate, Student, StudentState}
import org.openurp.code.std.model.StudentStatus

import java.time.LocalDate
import scala.util.control.Breaks.{break, breakable}

trait StudentService {
  def graduate(std: Student, endOn: LocalDate, graduated: StudentStatus): Unit
}
