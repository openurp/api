/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.fee.model

import java.time.Instant

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.model.Department
import org.openurp.edu.base.model.{Semester, Student}

/** 账单 */
class Bill extends LongId with Updated with Remark {

  /** 用户 */
  var std: Student = _

  /** 收费部门 */
  var depart: Department = _

  /** 发票号 */
  var invoiceCode: Option[String] = None

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
}