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

package org.openurp.edu.clazz.model

import org.beangle.data.model.Entity
import org.openurp.base.edu.model.{Direction, Major}
import org.openurp.base.model.Department
import org.openurp.base.std.model.Squad
import org.openurp.code.edu.model.{EducationLevel, EducationType}
import org.openurp.code.person.model.Gender
import org.openurp.code.std.model.{StdLabel, StdType}

import scala.language.implicitConversions

enum RestrictionMeta(val id: Int, contentType: Class[_]) {

  case Grade extends RestrictionMeta(1, classOf[String])

  case StdType extends RestrictionMeta(2, classOf[StdType])

  case Gender extends RestrictionMeta(3, classOf[Gender])

  case Department extends RestrictionMeta(4, classOf[Department])

  case Major extends RestrictionMeta(5, classOf[Major])

  case Direction extends RestrictionMeta(6, classOf[Direction])

  case Squad extends RestrictionMeta(7, classOf[Squad])

  case Level extends RestrictionMeta(8, classOf[EducationLevel])

  case EduType extends RestrictionMeta(9, classOf[EducationType])

  case StdLabel extends RestrictionMeta(11, classOf[StdLabel])

}
