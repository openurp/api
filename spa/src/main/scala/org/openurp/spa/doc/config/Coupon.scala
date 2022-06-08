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

package org.openurp.spa.doc.config

import java.time.{Instant, ZoneId}
import org.beangle.data.model.IntId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.{DateRange, Updated}

/**
 * 优惠券
 */
@config
class Coupon extends IntId with Updated with DateRange {

  def validAt(updatedAt: Instant): Boolean = {
    val updatedOn = updatedAt.atZone(ZoneId.systemDefault()).toLocalDate
    !(updatedOn.isBefore(beginOn) || updatedOn.isAfter(endOn))
  }

  /** 适合文档 */
  var docType: DocType = _

  /** 每个人可以领取的数量 */
  var countPerStd: Int = _

}
