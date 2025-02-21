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

package org.openurp.base.model

import org.beangle.data.model.Component
import org.beangle.data.model.pojo.Named

import java.time.Instant

trait AuditStep extends Component,Named {
  /** 受理人 */
  var assignee: Option[User] = None
  /** 审核时间 */
  var auditAt: Option[Instant] = None
  /** 审核意见 */
  var comments: Option[String] = None
  /** 审核结果 */
  var passed: Option[Boolean] = None
}
