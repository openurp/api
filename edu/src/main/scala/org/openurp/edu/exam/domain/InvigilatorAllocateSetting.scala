/*
 * Copyright (C) 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openurp.edu.exam.domain

/** 监考分配行为开关：是否只排首席、是否跨院系/跨校区同日、是否偏好同一天密集排监考等。 */
class InvigilatorAllocateSetting {

  var onlyChief: Boolean = true

  var acrossDepart: Boolean = true

  var acrossCampusOneDay: Boolean = false

  /** 是否禁用校区的配额限制，指按照总数进行分配 */
  var disableCampusQuota: Boolean = false

  /** 是否偏向于进行监考密集型的 */
  var preferCrowded: Boolean = true
}
