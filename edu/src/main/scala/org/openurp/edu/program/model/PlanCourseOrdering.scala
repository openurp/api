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

package org.openurp.edu.program.model

object PlanCourseOrdering extends Ordering[PlanCourse] {

  override def compare(o1: PlanCourse, o2: PlanCourse): Int = {
    if (o1.compulsory ^ o2.compulsory) {
      if o1.compulsory then -1 else 1
    } else {
      val termCmp = o1.terms.compareTo(o2.terms)
      if termCmp == 0 then o1.course.code.compareTo(o2.course.code) else termCmp
    }
  }
}
