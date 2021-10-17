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

package org.openurp.std.alter.model

import org.beangle.data.model.LongId

/**
 * 学籍异动项目
 */
class StdAlterationItem extends LongId {

  /**学籍异动*/
  var alteration: StdAlteration = _

  /**异动属性*/
  var meta: AlterMeta = _

  /** 变更前 */
  var oldvalue: Option[String] = None

  /** 变更前值 */
  var oldtext: Option[String] = None

  /** 变更后 */
  var newvalue: Option[String] = None

  /** 变更后值 */
  var newtext: Option[String] = None
}
