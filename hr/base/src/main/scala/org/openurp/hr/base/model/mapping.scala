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
package org.openurp.hr.base.model

import scala.reflect.runtime.universe

import org.beangle.data.model.annotation.code
import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    bind[DutyInfo].on(e => declare(
      e.staff & e.dutyType & e.dutyGrade & e.beginOn are notnull))

    bind[EducationInfo].on(e => declare(
      e.staff & e.beginOn are notnull,
      e.certificateNo is length(50),
      e.major is length(100),
      e.school is length(200)))

    bind[FamilyMember].on(e => declare(
      e.name is (notnull, length(50)),
      e.staff & e.familyRelationship & e.politicalStatus &
        e.jobStatus & e.nation & e.country & e.healthStatus are notnull,
      e.sid is (length(50), notnull)))

    bind[Health]

    bind[PostInfo].on(e => declare(
      e.staff & e.postType & e.postGrade & e.beginOn are notnull))

    bind[Staff].on(e => declare(
      e.code is (notnull, length(30)),
      e.person is (notnull),
      e.workStartOn & e.employType & e.employOn are notnull,
      e.states is (depends("staff")))).generator("auto_increment")

    bind[StaffState].on(e => declare(
      e.staff & e.department & e.status & e.beginOn is notnull))

    bind[TitleInfo].on(e => declare(
      e.staff & e.title & e.beginOn are notnull))

    bind[TutorInfo].on(e => declare(
      e.staff & e.tutorType & e.beginOn are notnull))

    bind[WorkInfo].on(e => declare(
      e.staff & e.workPlace & e.beginOn are notnull))

  }
}
