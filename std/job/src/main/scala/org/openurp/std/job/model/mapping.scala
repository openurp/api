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
package org.openurp.std.job.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[EmploymentStatus].declare { e =>
      e.code is(notnull, length(40))
      e.name is(notnull, length(80))
    }

    bind[Graduate].declare { e =>
      e.stdSource is length(100)
      e.session & e.std are notnull
    }
    bind[GraduateSession].declare { e =>
      e.code is(notnull, length(40))
      e.name is(notnull, length(80))
    }

    bind[StdEmployment].declare { e =>
      e.employmentStatus & e.std are notnull
    }
  }

}
