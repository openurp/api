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

class PlanMapping extends MappingModule {

  def binding(): Unit = {
    bind[CourseAuditResult].declare { e =>
      e.scores is(length(50), default("--"))
      e.remark is length(50)

    }
    bind[GroupAuditResult].declare { e =>
      e.name is length(100)
      e.children is depends("parent")
      e.courseResults is depends("groupResult")
    }
    bind[PlanAuditResult].declare { e =>
      e.groupResults is depends("planResult")
      e.remark is length(100)
      e.updates is length(500)
      index("", true, e.std)
    }
  }
}
