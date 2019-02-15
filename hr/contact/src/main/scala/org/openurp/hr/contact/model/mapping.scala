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

import scala.reflect.runtime.universe
import org.beangle.data.model.annotation.code
import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    bind[Typed].on(e => declare(
      e.typenames is length(100)))

    bind[Address].on(e => declare(
      e.pobox is length(50),
      e.extended is length(30),
      e.street is length(100),
      e.locality is length(80),
      e.region is length(50),
      e.postalCode is length(30),
      e.geo is length(50)))

    bind[Contact].on(e => declare(
      e.person is notnull,
      e.uuid is length(60),
      e.sortAs is length(100)))

    bind[Email].on(e => declare(
      e.contact is notnull,
      e.email is length(100)))

    bind[Geo]

    bind[Impp].on(e => declare(
      e.impp is length(100)))

    bind[Nickname].on(e => declare(
      e.name is length(100)))

    bind[Note].on(e => declare(
      e.note is length(500)))

    bind[Org].on(e => declare(
      e.unit is length(50),
      e.name is length(100)))

    bind[Photo].on(e => declare(
      e.uri is length(150)))

    bind[Related].on(e => declare(
      e.related is length(150)))

    bind[Role].on(e => declare(
      e.name is length(100)))

    bind[Telephone].on(e => declare(
      e.tel is length(100)))

    bind[Timezone].on(e => declare(
      e.timezone is length(100)))

    bind[Title].on(e => declare(
      e.name is length(100)))

    bind[Url].on(e => declare(
      e.url is length(150)))

    bind[Xtended].on(e => declare(
      e.xname is length(50),
      e.xvalue is length(200)))
  }
}
