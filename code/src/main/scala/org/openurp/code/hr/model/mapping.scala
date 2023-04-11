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

package org.openurp.code.hr.model

import org.beangle.data.orm.{IdGenerator, MappingModule}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator(classOf[Int], IdGenerator.Code)

    bind[StaffType].declare { e =>
      e.children.is(depends("parent"), orderby("code"))
    }
    bind[StaffSourceType].declare { e =>
      e.children.is(depends("parent"), orderby("code"))
    }
    bind[WorkStatus]
    bind[EmployType]

    bind[UserCategory]
    bind[DepartmentCategory].declare { e =>
      e.children is depends("parent")
    }
    all.cacheAll()
  }
}
