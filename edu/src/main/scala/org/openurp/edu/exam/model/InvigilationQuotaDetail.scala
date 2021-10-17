/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.edu.exam.model

import org.openurp.base.model.Campus
import org.openurp.base.model.Department
import org.beangle.data.model.LongId

/**
 * 监考人员配额明细
 *
 * @author chaostone
 */
class InvigilationQuotaDetail extends LongId {

  /** 监考人配额 */
  var quota: InvigilationQuota = _

  /** 校区 */
  var campus: Campus = _

  /** 开课部门 */
  var depart: Department = _

  /** 次数 */
  var amount: Float = _

  def this(campus: Campus, depart: Department, amount: Float) = {
    this()
    this.campus = campus
    this.depart = depart
    this.amount = amount
  }

}
