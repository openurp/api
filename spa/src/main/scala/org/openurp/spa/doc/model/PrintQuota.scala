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
package org.openurp.spa.doc.model

import java.time.Instant

import org.beangle.data.model.LongId
import org.openurp.base.model.User

/** 打印配额
 *
 */
class PrintQuota extends LongId {

  /** 学生 */
  var user: User = _

  /** 文档类型 */
  var docType: DocType = _

  /** 免支付打印张数 */
  var freeCnt: Int = _

  /** 打印张数 */
  var printCnt: Int = _

  /** 剩余免支付的张数 */
  var frees: Int = _

  /** 最后打印时间 */
  var lastPrintAt: Instant = _

  def this(user: User, docType: DocType) = {
    this()
    this.user = user
    this.docType = docType
  }
}
