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

import org.beangle.commons.model.Component

/**
 * 姓名
 * @see http://www.w3.org/International/questions/qa-personal-names
 * @see http://www.wikitree.com/wiki/Name_Fields
 */
class Name extends Component {

  /**名*/
  var givenName: Option[String] = _

  /**中间名*/
  var middleName: Option[String] = _

  /**姓*/
  var familyName: Option[String] = _

  /**姓名*/
  var formatedName: String = _

  override def toString: String = {
    formatedName
  }
}
