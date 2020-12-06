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
package org.openurp.hr.contact.model

trait Typed {

  var typenames: String = _

}

trait ValueTypes {

  def valuetypes: Set[String]
}

trait TextType extends ValueTypes {
  def valuetypes: Set[String] = {
    Set("text")
  }
}

trait UriType extends ValueTypes {
  def valuetypes: Set[String] = {
    Set("uri")
  }
}

trait UriTextType extends ValueTypes {
  def valuetypes: Set[String] = {
    Set("text", "uri")
  }
}

trait ContactAware{
  var contact:Contact =_
}
