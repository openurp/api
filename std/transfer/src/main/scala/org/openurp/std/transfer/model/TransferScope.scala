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
package org.openurp.std.transfer.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.openurp.edu.base.model.Major

import scala.collection.mutable

/** 招生学生范围
 *
 */
class TransferScope extends LongId {

  var scheme: TransferScheme = _

  /** 包含还是禁止   */
  var included: Boolean = _

  /**专业列表*/
  var majors: mutable.Buffer[Major] = Collections.newBuffer[Major]

  /**年级范围*/
  var grades: mutable.Buffer[String] = Collections.newBuffer[String]

}
