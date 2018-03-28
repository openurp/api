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
package org.openurp.edu.lesson

import scala.language.implicitConversions
import org.openurp.base.model.Department
import org.openurp.edu.base.code.model.{ Education, StdLabel, StdType }
import org.openurp.edu.base.model.{ Adminclass, Direction, Major }
import org.beangle.data.model.Entity

object LessonLimitMeta extends Enumeration {

  val Grade = new LimitMeta(1, classOf[String])

  val StdType = new LimitMeta(2, classOf[StdType])

  val Department = new LimitMeta(4, classOf[Department])

  val Major = new LimitMeta(5, classOf[Major])

  val Direction = new LimitMeta(6, classOf[Direction])

  val Adminclass = new LimitMeta(7, classOf[Adminclass])

  val Education = new LimitMeta(8, classOf[Education])

  val StdLabel = new LimitMeta(11, classOf[StdLabel])

  class LimitMeta(id: Int, val contentType: Class[_]) extends Val(id) {

    val contentValueType: Class[_] = {
      if (classOf[Entity[_]].isAssignableFrom(contentType)) classOf[Number] else contentType
    }
  }
  import scala.language.implicitConversions
  implicit def convertValue(v: Value): LimitMeta = v.asInstanceOf[LimitMeta]
}
