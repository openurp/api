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
package org.openurp.edu.student.info.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("date")

    bind[Admission].on(e => declare(
      e.std is notnull,
      e.letterNo is length(30)))

    bind[Examinee].on(e => declare(
      e.code is length(30),
      e.examNo is length(30),
      e.schoolNo is length(30),
      e.schoolName is length(200),
      e.province is length(50),
      e.scores is eleColumn("score")))

    bind[Graduation]

    bind[Home].on(e => declare(
      e.formerAddr is length(100),
      e.phone is length(20),
      e.postcode is length(20),
      e.address is length(150),
      e.police is length(150),
      e.policePhone is length(20)))

    bind[Contact].on(e => declare(
      e.mail is length(100),
      e.phone is length(20),
      e.mobile is length(20),
      e.address is length(150)))

    bind[EducationRecord]

    bind[SocialRelation]
  }

}
