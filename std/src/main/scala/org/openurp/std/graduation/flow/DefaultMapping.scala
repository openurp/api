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

package org.openurp.std.graduation.flow

import org.beangle.data.orm.MappingModule
import org.openurp.std.graduation.flow.{GradBachelor2ndApply, GradCompleteApply, GradDeferApply, GradDegreeApply}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {

    bind[GradBachelor2ndApply] declare { e =>
      e.gradeDetail is length(3000)
    }

    bind[GradDegreeApply] declare { e =>
      e.email is length(100)
      e.mobile is length(15)
      index("", true, e.batch, e.std)
    }

    bind[GradDeferApply] declare { e =>
      e.mobile is length(15)
      index("", true, e.batch, e.std)
    }

    bind[GradCompleteApply] declare { e =>
      e.mobile is length(15)
      index("", true, e.batch, e.std)
    }
  }
}
