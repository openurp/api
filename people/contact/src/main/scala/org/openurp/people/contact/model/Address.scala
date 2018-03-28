/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.people.contact.model

import org.beangle.data.model.LongId
import org.openurp.code.geo.model.Country
/**
 * 地址
 * @see https://tools.ietf.org/html/rfc6350#section-6.3.1
 */
class Address extends LongId with Preferred with Typed with TextType with ContactAware {

  /**国家地区*/
  var country: Country = _

  /**邮编*/
  var postalCode: String = _

  /**州/省份/直辖市*/
  var region: String = _

  /**地区/县/镇*/
  var locality: String = _

  /**街道*/
  var street: String = _

  /**其他详细说明*/
  var extended: String = _

  /**
   * 信箱
   * Post Office Box
   */
  var pobox: String = _

  /**地理方位*/
  var geo: String = _
  /**
   * ADR-component-pobox ";" ADR-component-ext ";"
   * ADR-component-street ";" ADR-component-locality ";"
   * ADR-component-region ";" ADR-component-code ";"
   * ADR-component-country
   */
  override def toString(): String = {
    pobox
  }

}
