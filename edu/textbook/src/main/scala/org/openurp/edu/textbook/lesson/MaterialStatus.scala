/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.textbook.lesson

object MaterialStatus extends Enumeration {

  val PUBLISHED = new Status("教材已发")

  val DONT_ASSIGNED = new Status("不需教材")

  val ASSIGNED = new Status("已指定")

  class Status private extends Val {

    var fullName: String = _

    def this(fullName: String) {
      this
      this.fullName = fullName
    }
  }
  import scala.language.implicitConversions
  implicit def convertValue(v: Value): Status = v.asInstanceOf[Status]
}
