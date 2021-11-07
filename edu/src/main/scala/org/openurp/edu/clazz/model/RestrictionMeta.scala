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

package org.openurp.edu.clazz.model

import scala.language.implicitConversions

import org.openurp.base.model.Department
import org.openurp.code.edu.model.EducationLevel
import org.openurp.base.edu.code.model.StdLabel
import org.openurp.base.edu.code.model.StdType
import org.openurp.base.edu.model.Direction
import org.openurp.base.edu.model.Major
import org.openurp.base.edu.model.Squad
import org.beangle.data.model.Entity

enum RestrictionMeta(val id:Int,contentType: Class[_]){

  case Grade extends RestrictionMeta(1, classOf[String])

  case StdType extends RestrictionMeta(2, classOf[StdType])

  case Department extends RestrictionMeta(4, classOf[Department])

  case Major extends RestrictionMeta(5, classOf[Major])

  case Direction extends RestrictionMeta(6, classOf[Direction])

  case Squad extends RestrictionMeta(7, classOf[Squad])

  case Level extends RestrictionMeta(8, classOf[EducationLevel])

  case StdLabel extends RestrictionMeta(11, classOf[StdLabel])

}
