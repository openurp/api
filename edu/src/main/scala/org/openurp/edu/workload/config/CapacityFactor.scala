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

package org.openurp.edu.workload.config

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.{Named, Remark, TemporalOn}

@config
class CapacityFactor extends LongId, Named, Remark, TemporalOn {

  var conditionExp: String = _

  var segments = Collections.newBuffer[FactorSegment]

}

@config
class FactorSegment extends LongId {

  var factor: CapacityFactor = _

  var minCapacity: Int = _

  var maxCapacity: Int = _

  var factorExp: String = _
}
