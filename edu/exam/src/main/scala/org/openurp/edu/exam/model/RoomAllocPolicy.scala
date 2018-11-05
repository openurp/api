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
package org.openurp.edu.exam.model

object RoomAllocPolicy extends Enumeration {

  class Policy(val name: String, val sameTask: Boolean,
               val sameCourse: Boolean, val sameDepart: Boolean) extends super.Val {
  }
  val SameTask = new Policy("只有一个任务", true, true, true)
  val SameCourse = new Policy("只有一个课程", false, true, true)
  val SameDepart = new Policy("只有一个开课院系", false, false, true)
  val Any = new Policy("任意安排", false, false, false)

}
