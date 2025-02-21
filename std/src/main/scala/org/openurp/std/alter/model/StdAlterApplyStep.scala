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

package org.openurp.std.alter.model

import org.beangle.commons.lang.Strings
import org.beangle.data.model.LongId
import org.openurp.base.model.{AuditStep, User}

import java.time.Instant

/** 学籍异动提交和审核记录
 */
class StdAlterApplyStep extends LongId, AuditStep {
  /** 审核顺序 */
  var idx: Int = _
  /** 申请记录 */
  var alterApply: StdAlterApply = _

  def this(apply: StdAlterApply, idx: Int, name: String) = {
    this()
    this.alterApply = apply
    this.idx = idx
    this.name = name
  }

  def audit(user: User, passed: Boolean, comments: Option[String]): Unit = {
    this.assignee = Some(user)
    this.passed = Some(passed)
    this.comments = comments
    this.auditAt = Some(Instant.now)
  }

  def assign(user: User): Unit = {
    this.assignee = Some(user)
  }
}
