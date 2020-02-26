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
package org.openurp.edu.clazz.model

object BookAdoption extends Enumeration {

  class State(id: Int, val fullname: String) extends super.Val {
  }

  val None = new State(0, "不使用教材，也不使用讲义")
  val UseTextBook = new State(1, "使用教材")
  val UseLecture = new State(2, "使用讲义")

  import scala.language.implicitConversions
  implicit def convertValue(v: Value): State = v.asInstanceOf[State]
}
