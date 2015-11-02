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
package org.openurp.people.info.model

import scala.reflect.runtime.universe
import org.beangle.data.model.annotation.code
import org.beangle.data.model.bind.Mapping

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")
    bind[FamilyFinance].on(e => declare(
      e.person & e.familyCategory & e.difficultyCause & e.difficultyDegree is notnull))

    bind[FamilyMember].on(e => declare(
      e.name is (notnull, length(50)),
      e.enName is length(200),
      e.person & e.familyRelationship & e.politicalStatus &
        e.jobStatus & e.nation & e.country & e.healthStatus & e.techPosition is notnull,
      e.sid is (length(50), notnull)))

    bind[FinanceAccount]

    bind[Health]

    bind[StudyHistory].on(e => declare(
      e.first is notnull))

    bind[WorkHistory]
  }
}
