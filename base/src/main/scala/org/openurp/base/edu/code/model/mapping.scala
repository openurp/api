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

package org.openurp.base.edu.code.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultCache("openurp.base.edu", "read-write")

    bind[BookType]
    bind[BookAwardType]
    bind[CourseType]
    bind[CourseAssessCategory]
    bind[CourseAbilityRate]
    bind[StdLabel]
    bind[StdLabelType]
    bind[StdType]
    bind[TeacherType] declare { e =>
      e.external is column("external_")
    }
  }
}
