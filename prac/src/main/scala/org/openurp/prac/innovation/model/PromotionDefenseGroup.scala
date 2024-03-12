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

package org.openurp.prac.innovation.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Named

import java.time.LocalDate
import scala.collection.mutable

/** 推优答辩组
 */
class PromotionDefenseGroup extends LongId, Named {

  var batch: Batch = _

  var capacity: Int = _

  /** 答辩日期 */
  var defenseOn: LocalDate = _

  /** 答辩开始时间 */
  var beginAt: HourMinute = _

  /** 答辩结束时间 */
  var endAt: HourMinute = _

  var location: String = _

  var members: mutable.Buffer[PromotionDefenseMember] = Collections.newBuffer[PromotionDefenseMember]

}
