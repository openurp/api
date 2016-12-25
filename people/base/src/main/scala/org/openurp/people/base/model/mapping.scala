/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2016, OpenURP Software.
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
package org.openurp.people.base.model

import scala.reflect.runtime.universe

import org.beangle.commons.model.annotation.code
import org.beangle.commons.model.bind.Mapping

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    bind[Person].on(e => declare(
      e.code is (notnull, unique, length(30)),
      e.idType & e.gender are (notnull),
      e.name.familyName & e.name.givenName are length(80),
      e.name.formatedName is length(100),
      e.name.middleName is length(50),
      e.formerName & e.phoneticName are length(100)))

    bind[PoliticalState].on(e => declare(
      e.person & e.status & e.beginOn are notnull))
  }
}
