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

package org.openurp.edu.clazz.domain

import org.beangle.commons.collection.Collections

import java.util

object NumSeqParser {
  def main(args: Array[String]): Unit = {
    val seqs = NumSeqParser.digest(Array(1, 2, 3, 5, 6, 7, 9, 10, 11, 17, 19, 21)).map { s =>
      if (s.step == 1) {
        if (s.start == s.end) s.start.toString
        else s"${s.start}-${s.end}"
      } else if (s.step == 2) {
        if (s.start % 2 == 1) s"${s.start}-${s.end}单"
        else s"${s.start}-${s.end}双"
      } else {
        s.toString
      }
    }
    println(seqs.mkString(" "))
  }

  class NumRange(var start: Int, var end: Int, var step: Int) {
    override def toString: String = {
      s"[$start,$end](${step})"
    }

    def accept(i: Int): Boolean = {
      val matched =
        if i == this.end then true
        else if i - this.end == step then true
        else false

      if matched then this.end = i
      matched
    }

    private def continuous(i: Int, s: Int = 1): NumRange = {
      new NumRange(i, i, s)
    }

    def copy(that: NumRange): Unit = {
      this.start = that.start
      this.end = that.end
      this.step = that.step
    }

    def next(number: Int): NumRange = {
      if (this.step == 2) {
        return continuous(number)
      }
      // 到这里就说明当前模式是连续周，那么就返回一个从头开始的连续周Pattern
      if (!(this.end == this.start)) {
        return continuous(number)
      }
      //到这里说明start==end，且是连续周 尝试用单、双模式来实验
      val next = continuous(this.start, 2)
      if next.accept(number) then next
      else continuous(number)
    }
  }

  /**
   * 根据输入的数字序列，返回[1-10]单，[2-10]双，[4-12]之类的文字
   *
   * @return 如果输入的是null或者长度为0的数组，返回""
   */
  def digest(nums: Array[Int]): Iterable[NumRange] = {
    if (nums == null || nums.length == 0) {
      return List.empty
    }
    util.Arrays.sort(nums)
    val patterns = Collections.newBuffer[NumRange]
    var last = new NumRange(nums(0), nums(0), 1)
    patterns.addOne(last)
    for (i <- 1 until nums.length) {
      val number: Int = nums(i)
      if (!last.accept(number)) {
        val next = last.next(number)
        if (last.start == next.start) {
          last.copy(next)
        } else {
          last = next
          patterns.addOne(last)
        }
      }
    }
    patterns
  }
}
