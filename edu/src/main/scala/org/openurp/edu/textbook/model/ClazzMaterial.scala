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

package org.openurp.edu.textbook.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.Textbook
import org.openurp.edu.clazz.model.Clazz
import org.openurp.base.edu.model.BookAdoption
import java.time.Instant
import scala.collection.mutable

/**
 * 教学材料说明
 * 包括教材、参考书等
 */
@deprecated("using clazz textbooks")
class ClazzMaterial extends LongId, Remark {

  /** 教学任务 */
  var clazz: Clazz = _

  /** 教材选用类型 */
  var adoption: BookAdoption = _

  /** 历史已经订购过 */
  var ordered: Boolean = _

  /** 教材列表 */
  var books: mutable.Set[Textbook] = Collections.newSet[Textbook]

  /** 参考书目 */
  var bibliography: Option[String] = None

  /** 其他教学资源 */
  var materials: Option[String] = None

  /** 是否审核通过 */
  var passed: Option[Boolean] = None

  /** 提交时间 */
  var submitAt: Option[Instant] = None

}
