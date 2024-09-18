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

package org.openurp.edu.course.model

import org.beangle.commons.lang.annotation.value

object ShortInterval {

  def apply(first: Short, second: Short): ShortInterval = {
    val a = first.toInt << 16 | second
    new ShortInterval(a)
  }
}

@value
class ShortInterval(val value: Int) extends Serializable, Ordered[ShortInterval] {
  override def compare(that: ShortInterval): Int = this.value - that.value

  def begin: Short = {
    (value >> 16).toShort
  }

  def end: Short = {
    (value & 0xffff).toShort
  }

  def length: Short = {
    (end - begin + 1).asInstanceOf[Short]
  }

  override def toString: String = {
    s"$begin-${end}"
  }
}
