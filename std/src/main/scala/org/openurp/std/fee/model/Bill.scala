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
import org.beangle.data.model.pojo.{Remark, Updatable}
import org.openurp.base.model.{Department, Semester}
import org.openurp.base.std.model.Student
import org.openurp.code.std.model.FeeType

import java.time.Instant
import scala.compiletime.uninitialized

/** 账单 */
class Bill extends LongId, Updatable, Remark {

  /** 用户 */
  var std: Student = uninitialized

  /** 收费部门 */
  var depart: Department = uninitialized

  /** 交费类型 */
  var feeType: FeeType = uninitialized

  /** 应缴费用(分) */
  var amount: Int = uninitialized

  /** 实收金额(分) */
  var payed: Int = uninitialized

  /** 学年度学期 */
  var semester: Semester = uninitialized

  /** 实缴时间 */
  var payAt: Option[Instant] = None

  /** 创建时间 */
  var createdAt: Instant = uninitialized

  /** 修改人 */
  var updatedBy: String = uninitialized

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
