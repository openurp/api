/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.edu.grade.course.domain

import org.openurp.edu.grade.course.model.CourseGrade

/** 加权平均值
 */
object WeightedMean {

  def calcGa(grades: collection.Iterable[CourseGrade]): Float = {
    var credits = 0f
    var creditGas = 0f
    for (grade <- grades) {
      if (grade.score.isDefined || !grade.passed) {
        val score = grade.score.getOrElse(0f)
        val credit = grade.course.credits
        credits += credit
        creditGas += credit * score
      }
    }
    if ((credits == 0)) 0f else (creditGas / credits)
  }

  def calcGpa(grades: collection.Iterable[CourseGrade]): Float = {
    var credits = 0f
    var creditGps = 0f
    for (grade <- grades if grade.gp.isDefined) {
      val credit = grade.course.credits
      credits += credit
      creditGps += credit * (grade.gp.get)
    }
    if ((credits == 0)) 0f else (creditGps / credits)
  }

}
