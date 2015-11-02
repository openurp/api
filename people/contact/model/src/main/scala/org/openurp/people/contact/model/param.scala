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
package org.openurp.people.contact.model

import org.beangle.commons.collection.Collections
import scala.collection.mutable.Buffer

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