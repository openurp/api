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

import java.text.Collator

import org.beangle.commons.bean.Properties
import org.openurp.edu.grade.course.model.CourseGrade
import org.openurp.code.edu.model.GradeType

/**
 * 可按照成绩组成部分排序的排序类
 */
class CourseGradeOrdering(cmpWhat: String, isAsc: Boolean, gradeTypes: Iterable[GradeType]) extends Ordering[CourseGrade] {

  val gradeTypeMap = gradeTypes.map(x => ("gradeType." + x.id, x)).toMap

  override def compare(g0: CourseGrade, g1: CourseGrade): Int = {
    if (cmpWhat.startsWith("gradeType")) {
      val gradeType = gradeTypeMap(cmpWhat).asInstanceOf[GradeType]
      val eg0 = g0.getGrade(gradeType)
      val eg1 = g1.getGrade(gradeType)
      if (eg0.isEmpty) -1
      else if (eg1.isEmpty) 1
      else cmpScore(eg0.get.score, eg1.get.score, isAsc)
    } else {
      val myCollator = Collator.getInstance
      val what0: Any = Properties.get(g0, cmpWhat)
      val what1: Any = Properties.get(g1, cmpWhat)
      if (isAsc) {
        myCollator.compare(if ((null == what0)) "" else what0.toString, if ((null == what1)) "" else what1.toString)
      } else {
        myCollator.compare(if ((null == what1)) "" else what1.toString, if ((null == what0)) "" else what0.toString)
      }
    }
  }

  private def cmpScore(score0: Option[Float], score1: Option[Float], isAsc: Boolean): Int = {
    val fs0 = score0.getOrElse(0f)
    val fs1 = score1.getOrElse(0f)
    if (isAsc) {
      java.lang.Float.compare(fs0, fs1)
    } else {
      java.lang.Float.compare(fs1, fs0)
    }
  }
}
