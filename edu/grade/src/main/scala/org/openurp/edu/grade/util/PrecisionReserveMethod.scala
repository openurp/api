/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.util

/**
 * 保留数字精确位的方法
 *
 * @author chaostone
 */
trait PrecisionReserveMethod {

  def reserve(num: Float, precision: Int): Float

  def reserve(num: Double, precision: Int): Double
}

object PrecisionReserveMethod {

  /**
   * 四舍五入进位法
   */
  object MoreHalf extends PrecisionReserveMethod {
    def reserve(value: Float, precision: Int): Float = {
      val mutilply = Math.pow(10, precision + 1).toInt
      var num = value
      num *= mutilply
      if (num % 10 >= 5) num += 10
      num -= num % 10
      num / mutilply
    }

    def reserve(value: Double, precision: Int): Double = {
      val mutilply = Math.pow(10, precision + 1).toInt
      var num = value
      num *= mutilply
      if (num % 10 >= 5) num += 10
      num -= num % 10
      num / mutilply
    }
  }

}