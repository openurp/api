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

package org.openurp.base.std.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, EnNamed, Named, YearMonthRange}
import org.openurp.base.model.Project
import scala.compiletime.uninitialized

/** 学生入学年级
 * */
class Grade extends LongId, Coded, Named, EnNamed, YearMonthRange {

  var project: Project = uninitialized

  def this(id: Long, code: String, name: String) = {
    this()
    this.id = id
    this.code = code
    this.name = name
  }

  override def toString: String = {
    name
  }
}
