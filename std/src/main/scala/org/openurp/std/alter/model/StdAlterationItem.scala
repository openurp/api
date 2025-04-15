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

/**
 * 学籍异动项目
 */
class StdAlterationItem extends LongId {
  /** 学籍异动 */
  var alteration: StdAlteration = _
  /** 异动属性 */
  var meta: AlterMeta = _
  /** 变更前 */
  var oldvalue: Option[String] = None
  /** 变更前值 */
  var oldtext: Option[String] = None
  /** 变更后 */
  var newvalue: Option[String] = None
  /** 变更后值 */
  var newtext: Option[String] = None

  def this(meta: AlterMeta, ov: Option[String], ot: Option[String], nv: Option[String], nt: Option[String]) = {
    this()
    this.meta = meta
    this.oldvalue = ov
    this.oldtext = ot
    this.newvalue = nv
    this.newtext = nt
  }

  def this(meta: AlterMeta, ov: String, ot: String, nv: String, nt: String) = {
    this()
    this.meta = meta
    this.oldvalue = if Strings.isBlank(ov) then None else Some(ov)
    this.oldtext = if Strings.isBlank(ot) then None else Some(ot)
    this.newvalue = if Strings.isBlank(nv) then None else Some(nv)
    this.newtext = if Strings.isBlank(nt) then None else Some(nt)
  }
}
