/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.base.edu

/**
 * 通用的审核状态枚举类
 *
 */
object AuditStates extends Enumeration {
  class State(name: String) extends super.Val(name)

  val Draft = new State("草稿")
  val Submited = new State("已提交")

  val Accepted = new State("初审通过")
  val UnAccepted = new State("初审不通过")

  val Finalized = new State("通过")
  val Rejected = new State("不通过")

}
