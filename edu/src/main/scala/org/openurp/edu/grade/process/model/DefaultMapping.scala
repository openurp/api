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
package org.openurp.edu.grade.process.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[ProcessGrade].declare { e =>
      e.tests is depends("processGrade")

      index("", true, e.std, e.clazz)
      index("", false, e.std)
      index("", false, e.clazz)
    }

    bind[ProcessGradeState].declare { e =>
      index("", true, e.clazz)
    }

    bind[ProcessTestGrade].declare { e =>
      index("", true, e.processGrade, e.testType)
    }

    bind[ProcessTestType].declare { e =>
      index("", true, e.name)
    }
  }
}
