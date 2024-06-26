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

package org.openurp.std.graduation.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[GraduateBatch].declare { e =>
      e.name is length(100)
    }
    bind[Graduation] declare { e =>
      index("", true, e.batch, e.std)
    }

    bind[GraduateResult].declare { e =>
      e.passedItems & e.failedItems are length(500)
      index("", true, e.batch, e.std)
    }

    bind[DegreeResult].declare { e =>
      e.passedItems & e.failedItems are length(500)
      index("", false, e.std)
    }

    bind[Bachelor2ndApply] declare { e =>
      e.gradeDetail is length(3000)
    }

    bind[DegreeApply] declare { e =>
      index("", true, e.std)
    }
    bind[PlanResultCheck] declare { e =>
      e.contents is length(3000)
      index("", true, e.std)
    }
  }
}
