/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.hr.base.model

import scala.reflect.runtime.universe

import org.beangle.data.model.annotation.code
import org.beangle.data.model.bind.Mapping

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    bind[Staff].on(e => declare(
      e.code is (notnull, length(30)),
      e.person is (notnull),
      e.states is (depends("staff")))).generator("auto_increment")

    bind[TitleInfo].on(e => declare(
      e.staff & e.title & e.beginOn is notnull))

    bind[TutorInfo].on(e => declare(
      e.staff & e.tutorType & e.beginOn is notnull))

    bind[StaffState].on(e => declare(
      e.staff & e.department & e.status & e.beginOn is notnull))
  }
}
