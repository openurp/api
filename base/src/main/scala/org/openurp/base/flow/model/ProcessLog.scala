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
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.{AuditStatus, User}

/** 流程审核日志 */
class ProcessLog extends LongId, Updated {

  var flowType: FlowType = _

  /** 实体 */
  var entityId: Long = _

  /** 起始状态 */
  var fromStatus: String = _

  /** 结束状态 */
  var toStatus: String = _

  /** 操作人 */
  var operator: User = _

  /** 说明 */
  var comments: String = _
}
