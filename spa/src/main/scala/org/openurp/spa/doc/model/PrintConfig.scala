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

package org.openurp.spa.doc.model

import org.beangle.data.model.IntId
import org.beangle.data.model.pojo.{TemporalOn, Updated}

/** 打印配置
 * 针对每种类型的文档配置单价和打印的上限
 */
class PrintConfig extends IntId with Updated with TemporalOn {

  /** 文档类型 */
  var docType: DocType = _

  /** 单价，以分为单位，免费设置为0 */
  var price: Int = _

  /** 最多打印次数 */
  var maxLimit: Int = _
}
