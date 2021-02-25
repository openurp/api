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
package org.openurp.std.award.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
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
    bind[HonorAward]
    bind[StipendAward]
    bind[SubsidyAward]
  }

}
