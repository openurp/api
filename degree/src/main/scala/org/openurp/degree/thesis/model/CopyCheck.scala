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

package org.openurp.degree.thesis.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated

/** 拷贝检测
 *
 */
class CopyCheck extends LongId with Updated {

  var writer: Writer = _

  /** 复制比 */
  var copyRatio: Float = _

  /** 去除引用文献复制比 */
  var copyRatioExtRefer: Float = _

  /** 总字数 */
  var wordCount: Int = _

  /** 复制字数 */
  var copyWordCount: Int = _

  /**检测报告地址*/
  var report: Option[String] = None
}
