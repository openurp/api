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

package org.openurp.std.fee.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.model.{Department, Semester}
import org.openurp.base.std.model.Student
import org.openurp.code.std.model.FeeType

import java.time.Instant

/** 账单 */
class Bill extends LongId, Updated, Remark {

  /** 用户 */
  var std: Student = _

  /** 收费部门 */
  var depart: Department = _

  /** 交费类型 */
  var feeType: FeeType = _

  /** 应缴费用(分) */
  var amount: Int = _

  /** 实收金额(分) */
  var payed: Int = _

  /** 学年度学期 */
  var semester: Semester = _

  /** 实缴时间 */
  var payAt: Option[Instant] = None

  /** 创建时间 */
  var createdAt: Instant = _

  /** 修改人 */
  var updatedBy: String = _

  /** 应缴费用(元)
   *
   * @return
   */
  def amountYuan: Float = {
    amount / 100.0f
  }

  /** 实收金额(元)
   *
   * @return
   */
  def payedYuan: Float = {
    payed / 100.0f
  }

}
