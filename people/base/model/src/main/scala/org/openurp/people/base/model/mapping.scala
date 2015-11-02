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
package org.openurp.people.base.model

import scala.reflect.runtime.universe

import org.beangle.data.model.annotation.code
import org.beangle.data.model.bind.Mapping

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    bind[Person].on(e => declare(
      e.code is (notnull, unique, length(30)),
      e.idType & e.gender are (notnull),
      e.name.familyName & e.name.givenName are length(80),
      e.name.formatedName is length(100),
      e.name.middleName is length(50),
      e.formerName & e.phoneticName are length(100))).generator("auto_increment")
  }
}
