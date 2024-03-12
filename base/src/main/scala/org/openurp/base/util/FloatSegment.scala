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

package org.openurp.base.util

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.Objects

/**
 * 统计段<br>
 * 排序按照降序进行 [min,max]
 *
 *
 */
object FloatSegment {
  /**
   * 构造分段区[start,start+level-1],[start+level,start+2*level-1]..
   *
   * @param start
   * @param level
   * @param count
   * @return
   */
  def build(start: Int, level: Int, count: Int): Seq[FloatSegment] = {
    val l = Collections.newBuffer[FloatSegment]
    var begin = start
    for (i <- 0 until count) {
      l.addOne(new FloatSegment(begin.toFloat, (begin + level - 1).toFloat))
      begin += level
    }
    l.toSeq
  }

  def count(numbers: Iterable[Number], segs: Seq[FloatSegment]): Unit = {
    numbers foreach { n =>
      if (null != n) {
        segs.find(_.contains(n.floatValue())).foreach { g => g.count += 1 }
      }
    }
  }
}

class FloatSegment(val min: Float, val max: Float) extends Ordered[FloatSegment] {
  var count = 0

  def contains(score: Float): Boolean = {
    java.lang.Float.compare(score, max) <= 0 && java.lang.Float.compare(score, min) >= 0
  }

  def add(score: Float): Boolean = {
    if (contains(score)) {
      count += 1
      true
    }
    else false
  }

  /**
   * @see java.lang.Comparable#compareTo(Object)
   */
  override def compare(a: FloatSegment): Int = {
    java.lang.Float.compare(a.min, this.min)
  }

  override def clone: FloatSegment = new FloatSegment(min, max)

  def emptySeg: Boolean = if (min == 0 && max == 0) true
  else false

  /**
   * @see java.lang.Object#toString()
   */
  override def toString: String = {
    Objects.toStringBuilder(this.getClass).add("min", this.min).add("max", this.max).add("count", this.count).toString
  }
}
