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

import java.time.Instant

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, Remark}
import org.openurp.base.std.model.Student
import scala.compiletime.uninitialized

/** 支付订单记录
 * */
class Order extends LongId , Coded , Remark {

  /** 用户 */
  var std: Student = uninitialized

  /** 金额（分） */
  var amount: Int = uninitialized

  /** 支付渠道 */
  var channel: Option[String] = None

  /** 支付时间 */
  var payAt: Option[Instant] = None

  /** 账单 */
  var bill: Bill = uninitialized

  /** 是否成功 */
  var paid: Boolean = uninitialized

  /**状态*/
  var status: String = uninitialized

  /**支付地址*/
  var payUrl: String = uninitialized

  /** 创建时间点 */
  var createdAt: Instant = uninitialized

  /** 过期时间点 */
  var expiredAt: Instant = uninitialized

  /** 发票地址 */
  var invoicePath: Option[String] = None

}
