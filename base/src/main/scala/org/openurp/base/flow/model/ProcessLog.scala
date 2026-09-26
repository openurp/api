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

package org.openurp.base.flow.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updatable
import org.openurp.base.model.{AuditStatus, User}
import scala.compiletime.uninitialized

/** 流程审核日志 */
class ProcessLog extends LongId, Updatable {

  var flowType: FlowType = uninitialized

  /** 实体 */
  var entityId: Long = uninitialized

  /** 起始状态 */
  var fromStatus: String = uninitialized

  /** 结束状态 */
  var toStatus: String = uninitialized

  /** 操作人 */
  var operator: User = uninitialized

  /** 说明 */
  var comments: String = uninitialized
}
