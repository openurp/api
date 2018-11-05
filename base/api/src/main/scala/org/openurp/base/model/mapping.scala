/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright (c) 2005, The OpenURP Software.
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
package org.openurp.base.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")
    defaultCache("openurp.base", "read-write")

    bind[School] on (e => declare(
      e.code is (length(10), unique),
      e.name is length(50)))

    bind[Department] on (e => declare(
      e.code is length(10),
      e.name is length(80),
      e.enName is length(100),
      e.shortName is length(100),
      e.indexno is length(20),
      e.children is depends("parent"),
      e.remark is length(200)))

    bind[Campus] on (e => declare(
      e.code is length(10),
      e.name is length(80),
      e.enName & e.shortName are (length(100)),
      e.remark is (length(200))))

    bind[Building] on (e => declare(
      e.code is length(10),
      e.name is length(80),
      e.enName & e.shortName are (length(100)),
      e.remark is (length(200))))

    bind[Room] on (e => declare(
      e.code is length(10),
      e.name is length(80),
      e.remark is (length(200))))

    bind[User] on (e => declare(
      e.code is length(30),
      e.name is length(80),
      e.email is length(80),
      e.mobile is length(15),
      e.remark is length(200)))

    all.except(classOf[User]).cacheable()

  }

}
