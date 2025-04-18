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

package org.openurp.std.spa.config

import org.beangle.data.model.IntId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.{DateRange, Updated}
import org.openurp.base.model.Project
import org.openurp.code.std.model.StdDocType

import java.time.{Instant, ZoneId}

/**
 * 优惠券
 */
@config
class SpaCoupon extends IntId, Updated, DateRange {

  /** 项目 */
  var project: Project = _

  def validAt(updatedAt: Instant): Boolean = {
    val updatedOn = updatedAt.atZone(ZoneId.systemDefault()).toLocalDate
    !(updatedOn.isBefore(beginOn) || updatedOn.isAfter(endOn))
  }

  /** 适合文档 */
  var docType: StdDocType = _

  /** 每个人可以领取的数量 */
  var countPerStd: Int = _

}
