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
package org.openurp.edu.award.model

import scala.reflect.runtime.universe
import org.beangle.data.model.annotation.code
import org.beangle.data.model.bind.Mapping
import org.openurp.edu.award.code.model.{ HonorCategory, HonorLevel }
import org.openurp.edu.award.code.model.HonorType
import org.openurp.edu.award.code.model.ScholarshipCategory
import org.openurp.edu.award.code.model.ScholarshipLevel
import org.openurp.edu.award.code.model.ScholarshipType
import org.openurp.edu.award.code.model.StipendLevel
import org.openurp.edu.award.code.model.SubsidyLevel
import org.openurp.edu.award.code.model.SubsidyCategory
import org.openurp.edu.award.code.model.StipendCategory
import org.openurp.edu.award.results.model.ScholarshipAward
import org.openurp.edu.award.results.model.StdHonorAward
import org.openurp.edu.award.results.model.StipendAward
import org.openurp.edu.award.results.model.SubsidyAward

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    //code
    bind[HonorCategory].on(e => declare(
      e.honorType is notnull))
    bind[HonorLevel]
    bind[HonorType]

    bind[ScholarshipCategory].on(e => declare(
      e.scholarshipType is notnull))
    bind[ScholarshipLevel].on(e => declare(
      e.scholarshipCategory is notnull))
    bind[ScholarshipType]

    bind[StipendCategory]
    bind[StipendLevel].on(e => declare(
      e.stipendCategory is notnull))
    bind[SubsidyCategory]
    bind[SubsidyLevel].on(e => declare(
      e.subsidyCategory is notnull))

    //result
    bind[ScholarshipAward].on(e => declare(
      e.scholarshipCategory & e.scholarshipLevel & e.std & e.semester are notnull))

    bind[StdHonorAward].on(e => declare(
      e.honorCategory & e.honorLevel & e.std & e.semester are notnull))

    bind[StipendAward].on(e => declare(
      e.stipendCategory & e.stipendLevel & e.std & e.semester are notnull))

    bind[SubsidyAward].on(e => declare(
      e.subsidyCategory & e.subsidyLevel & e.std & e.semester are notnull))

  }

}
