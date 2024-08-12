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

package org.openurp.edu.grade.model

import org.beangle.data.orm.MappingModule

class RegularMapping extends MappingModule {

  def binding(): Unit = {
    bind[RegularGradeState].declare { e =>
      e.items is depends("state")
      index("", true, e.clazz)
    }

    bind[RegularGradeStateItem]

    bind[RegularGrade].declare { e =>
      e.items is depends("grade")

      index("", true, e.std, e.clazz)
      index("", false, e.std)
      index("", false, e.clazz)
    }

    bind[RegularGradeItem].declare { e =>
      index("", true, e.grade, e.component)
    }

    bind[RegularComponent].declare { e =>
      index("", true, e.name)
    }
  }
}
