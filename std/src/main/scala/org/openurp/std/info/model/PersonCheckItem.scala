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

package org.openurp.std.info.model

import org.beangle.data.model.LongId

/** 基本信息更改项目
 */
class PersonCheckItem extends LongId {

  var check: PersonCheck = _

  var field: PersonField = _

  var oldValue: String = _

  var newValue: String = _

  def this(check: PersonCheck, field: PersonField, oldValue: String, newValue: String) = {
    this()
    this.check = check
    this.field = field
    this.oldValue = oldValue
    this.newValue = newValue
  }

  def update(oldValue: String, newValue: String): Unit = {
    this.oldValue = oldValue
    this.newValue = newValue
  }
}
