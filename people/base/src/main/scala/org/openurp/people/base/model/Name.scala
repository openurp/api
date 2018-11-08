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
package org.openurp.people.base.model

import org.beangle.data.model.Component

/**
 * 姓名
 * @see http://www.w3.org/International/questions/qa-personal-names
 * @see http://www.wikitree.com/wiki/Name_Fields
 */
class Name extends Component {

  /**名*/
  var givenName: Option[String] = None

  /**中间名*/
  var middleName: Option[String] = None

  /**姓*/
  var familyName: Option[String] = None

  /**姓名*/
  var formatedName: String = _

  override def toString: String = {
    formatedName
  }
}
