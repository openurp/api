/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
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
package org.openurp.sin.harvest.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("date")

    bind[AssumeRole].on(e => declare(
      e.code is (notnull, length(10)),
      e.name is (notnull, length(80))))

    bind[HarvestType].on(e => declare(
      e.code is (notnull, length(10)),
      e.name is (notnull, length(80))))

    bind[Literature].on(e => declare(
      e.name is (notnull, length(300)),
      e.introduction is length(2000),
      e.department & e.researcher are notnull,
      e.isbn is length(100)))

    bind[LiteratureMember].on(e => declare(
      e.name is length(80),
      e.literature is notnull))
    bind[PublishedRange].on(e => declare(
      e.code is (notnull, length(10)),
      e.name is (notnull, length(80))))

    bind[PublishedSituation].on(e => declare(
      e.name is (notnull, length(300)),
      e.enName is length(200),
      e.isbn & e.issn & e.cn are length(100),
      e.publishedDate & e.publishedRange are notnull))

    bind[Researcher].on(e => declare(
      e.person is notnull))

    bind[ThesisHarvest].on(e => declare(
      e.researcher & e.publishedSituation & e.department is notnull,
      e.name is length(300),
      e.summary is length(2000)))
  }

}
