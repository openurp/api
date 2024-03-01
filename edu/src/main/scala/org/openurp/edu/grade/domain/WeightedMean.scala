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

package org.openurp.edu.grade.domain

import org.openurp.edu.grade.model.CourseGrade

/** 加权平均值
 */
object WeightedMean {

  def calcGa(grades: collection.Iterable[CourseGrade]): BigDecimal = {
    if grades.isEmpty then BigDecimal(0)
    else
      var credits: Int = 0
      var creditGas: Long = 0L
      val level = grades.head.std.level
      for (grade <- grades) {
        if (grade.score.isDefined || !grade.passed) {
          val score = (grade.score.getOrElse(0f) * 1000).toLong
          val credit = (grade.course.getCredits(level) * 100).toInt
          credits += credit
          creditGas += credit * score
        }
      }
      if (credits == 0) BigDecimal(0) else BigDecimal(creditGas) / BigDecimal(credits * 1000)
  }

  def calcGpa(grades: collection.Iterable[CourseGrade]): BigDecimal = {
    if grades.isEmpty then 0f
    else
      var credits = 0
      var creditGps = 0L
      val level = grades.head.std.level
      for (grade <- grades if grade.gp.isDefined) {
        val credit = (grade.course.getCredits(level) * 100).toInt
        credits += credit
        creditGps += credit * (grade.gp.get * 100).toInt
      }
      if (credits == 0) BigDecimal(0) else BigDecimal(creditGps) / BigDecimal(credits * 100)
  }

}
