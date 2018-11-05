/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright (c) 2005, The OpenURP Software.
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
package org.openurp.edu.grade.course.domain

/**
 * 保留数字精确位的方法
 */
trait NumRounder {

  def round(num: Float, precision: Int): Float

  def round(num: Double, precision: Int): Double

}

object NumRounder {
  /**
   * 向上取整法
   */
  object Ceil extends NumRounder {

    def round(num: Float, precision: Int): Float = {
      val mutilply = Math.pow(10, precision + 1).toInt
      var res = num * mutilply
      if (res % 10 >= 1) res += 10
      res -= res % 10
      res / mutilply
    }

    def round(num: Double, precision: Int): Double = {
      val mutilply = Math.pow(10, precision + 1).toInt
      var res = num * mutilply
      if (res % 10 >= 1) res += 10
      res -= res % 10
      res / mutilply
    }
  }

  /**
   * 四舍五入进位法
   */
  object Normal extends NumRounder {

    def round(n: Float, precision: Int): Float = {
      var num = n
      val mutilply = Math.pow(10, precision + 1).toInt
      num *= mutilply
      if (num % 10 >= 5) num += 10
      num -= num % 10
      num / mutilply
    }

    def round(n: Double, precision: Int): Double = {
      var num = n
      val mutilply = Math.pow(10, precision + 1).toInt
      num *= mutilply
      if (num % 10 >= 5) num += 10
      num -= num % 10
      num / mutilply
    }
  }

}
