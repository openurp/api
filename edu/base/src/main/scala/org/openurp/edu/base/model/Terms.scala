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
package org.openurp.edu.base.model

import org.beangle.commons.lang.annotation.value
import org.beangle.commons.lang.{Numbers, Strings}

object Terms {
  val empty = new Terms(0)

  def apply(terms: String): Terms = {
    if (Strings.isNotBlank(terms) && "*" != terms) {
      var value = 0
      Strings.split(terms, ",") foreach { t =>
        value |= 1 << Numbers.toInt(t);
      }
      new Terms(value)
    } else {
      empty
    }
  }
}

@value
class Terms(val value: Int) extends Ordered[Terms] with Serializable {

  override def compare(other: Terms): Int = {
    if (this.value < other.value) -1
    else if (this.value == other.value) 0
    else 1
  }

  def contains(term: Int): Boolean = {
    (value & (1 << term)) > 0
  }

  def termList: List[Int] = {
    if (value > 0) {
      val str = java.lang.Integer.toBinaryString(value)
      var i = str.length - 1
      val result = new collection.mutable.ListBuffer[Int]
      while (i >= 0) {
        if (str.charAt(i) == '1') result += (str.length - i - 1)
        i -= 1
      }
      result.toList
    } else {
      List.empty
    }
  }

  def matches(other: Terms): Boolean = {
    (this.value & other.value) > 0
  }

  override def toString: String = {
    termList.mkString(",")
  }
}
