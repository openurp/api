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
package org.openurp.edu.student.alter.model

import org.beangle.data.orm.MappingModule
import org.openurp.edu.student.info.model.Contact
import org.openurp.edu.student.info.model.Admission

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("date")

    bind[StdAlteration].on(e => declare(
      e.std is notnull,
      e.items is depends("alteration")))

    bind[StdAlterationItem].on(e => declare(
      e.oldvalue is length(100),
      e.oldvalue is length(100)))
  }

}
