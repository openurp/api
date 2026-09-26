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

package org.openurp.base.log

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.log

import java.time.Instant
import scala.compiletime.uninitialized

@log
abstract class LogEntry extends LongId {
  /** 操作人 */
  var operator: String = uninitialized
  /** 操作时间 */
  var operateAt: Instant = uninitialized
  /** 操作内容摘要 */
  var summaries: String = uninitialized
  /** 操作内容 */
  var details: String = uninitialized
  /** 对应的资源 */
  var resources: String = uninitialized
  /** IP */
  var ip: String = uninitialized
  /** 操作客户端代理 */
  var agent: String = uninitialized
  /** 访问入口 */
  var entry: String = uninitialized
}
