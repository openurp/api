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
package org.openurp.hr.base.model

import org.openurp.people.base.model.Person
import org.openurp.code.person.model.HealthStatus
import org.openurp.code.person.model.BloodType
import org.beangle.commons.model.LongId

/**
 * 健康状况
 */
class Health extends LongId {
  /**教职工*/
  var staff: Staff = _
  /**健康状况*/
  var healthStatus: HealthStatus = _
  /**血型*/
  var bloodType: BloodType = _
  /**身高*/
  var height: Float = _
  /**体重*/
  var weight: Float = _
}
