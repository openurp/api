/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2016, OpenURP Software.
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
import org.beangle.commons.model.annotation.code
import org.beangle.commons.model.bind.Mapping
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
    bind[HonorCategory]
    bind[HonorLevel]
    bind[HonorType]

    bind[ScholarshipCategory]
    bind[ScholarshipLevel]
    bind[ScholarshipType]

    bind[StipendCategory]
    bind[StipendLevel]
    bind[SubsidyCategory]
    bind[SubsidyLevel]
    
    //result
    bind[ScholarshipAward]
    bind[StdHonorAward]
    bind[StipendAward]
    bind[SubsidyAward]
  }

}
