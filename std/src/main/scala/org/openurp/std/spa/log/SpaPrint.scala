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

package org.openurp.std.spa.log

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.log
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.model.User
import org.openurp.code.std.model.StdDocType

/** 打印流水
 * 记录每次打印文档的流水
 */
@log
class SpaPrint extends LongId, Updated, Remark {
  /** 学生 */
  var user: User = _

  /** 文档类型 */
  var docType: StdDocType = _

  /** 支付费用 */
  var payed: Int = _

  /** 打印ip */
  var ip: String = _

}
