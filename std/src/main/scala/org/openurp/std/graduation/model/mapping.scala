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
package org.openurp.std.graduation.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[GraduateSession].declare { e =>
      e.name is length(100)
    }
    bind[GraduateAuditItem].declare { e =>
      e.name is length(100)
      e.comments is length(500)
    }
    bind[DegreeAuditItem].declare { e =>
      e.name is length(100)
      e.comments is length(500)
    }
    bind[GraduateResult].declare { e =>
      e.items is depends("result")
      e.comments.is(column("graduate_comments"), length(500))
    }
    bind[DegreeResult].declare { e =>
      e.items is depends("result")
      e.comments.is(column("degree_comments"), length(500))
    }

    bind[DegreeApply]
  }

}
