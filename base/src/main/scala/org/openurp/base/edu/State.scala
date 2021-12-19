/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.base.edu

/**
 * 通用的审核状态枚举类
 *
 */
enum AuditStates(val name: String) {
  case Draft extends AuditStates("草稿")
  case Submited extends AuditStates("已提交")

  case Accepted extends AuditStates("初审通过")
  case UnAccepted extends AuditStates("初审不通过")

  case Finalized extends AuditStates("通过")
  case Rejected extends AuditStates("不通过")

  override def toString: String = name
}
