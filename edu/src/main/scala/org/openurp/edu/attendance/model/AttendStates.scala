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

package org.openurp.edu.attendance.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.value

object AttendStates {
  val Empty = new AttendStates(0)

  def apply(states: AttendState*): AttendStates = {
    var v = 0L
    var i = 1
    while (i <= states.size) {
      v |= (states(i - 1).id.toLong << (i - 1) * 3)
      i += 1
    }
    new AttendStates(v)
  }
}

/** 使用Long型表示最多21次考勤结果
 *
 * @param value
 */
@value
class AttendStates(val value: Long) extends AnyVal, Serializable {

  def apply(i: Int): AttendState = {
    val t = value >> (i - 1) * 3
    val id = (t & 7).toInt
    AttendState.fromOrdinal(id)
  }

  def add(i: Int, status: AttendState): AttendStates = {
    var template = java.lang.Long.MAX_VALUE ^ (7L << (i - 1) * 3)
    template &= value
    template |= status.id.toLong << (i - 1) * 3
    new AttendStates(template)
  }

  def remove(i: Int): AttendStates = {
    val template = java.lang.Long.MAX_VALUE ^ (7L << (i - 1) * 3)
    new AttendStates(template & value)
  }

  def values: collection.Map[Int, AttendState] = {
    var i = 1
    val v = value
    val states = Collections.newMap[Int, AttendState]
    while (i <= 21) {
      val t = v >> (i - 1) * 3
      val id = (t & 7).toInt
      if id > 0 then states.put(i, AttendState.fromOrdinal(id))
      i += 1
    }
    states
  }

}
