/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
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
package org.openurp.hr.info.model

import scala.reflect.runtime.universe

import org.beangle.data.model.bind.Mapping

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    bind[DegreeInfo].on(e => declare(
      e.staff & e.beginOn are notnull,
      e.conferralBy is length(200),
      e.certificateNo is length(50),
      e.major is length(100)))

    bind[DutyInfo].on(e => declare(
      e.staff & e.dutyType & e.dutyGrade & e.beginOn are notnull))

    bind[PostInfo].on(e => declare(
      e.staff & e.postType & e.postGrade & e.beginOn are notnull))

    bind[SourceInfo].on(e => declare(
      e.staff & e.workStartOn & e.employType & e.employOn are notnull))
  }

}
