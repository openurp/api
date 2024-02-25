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
import org.beangle.data.model.pojo.{Remark, Updated}

/** 论文提交信息
 */
class PaperSubmission extends LongId, Updated, Remark {
  var writer: Writer = _

  /** 题目 */
  var title: String = _

  /** 附件路径 */
  var filePath: String = _

  /** 附件类型 */
  var fileExt: String = _

  /** 文件sha1摘要 */
  var sha1sum: String = _
}
