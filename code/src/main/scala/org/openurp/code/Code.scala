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

package org.openurp.code

import org.beangle.data.model.IntId
import org.beangle.data.model.pojo.*

trait Code extends IntId, Named, EnNamed, Coded {
}

object CodeCategory {

  //  基础代码种类
  val Nation = "nation"

  val Industry = "industry"

  val School = "school"
}

abstract class CodeBean extends IntId, Code, TemporalOn, Updated, Remark {
  def codeName: String = s"$code $name"

  def enName2: String = {
    enName.getOrElse(name)
  }
}
