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

package org.openurp.base.config.model

import org.beangle.data.orm.{IdGenerator, MappingModule}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultCache("openurp.base", "read-write")

    bind[Business].declare { e =>
      e.code is length(50)
      e.name is length(100)
    }.generator(IdGenerator.AutoIncrement)

    bind[RuleMeta] declare { e =>
      e.name is length(50)
      e.title is length(80)
      e.description is length(500)
      e.params is depends("ruleMeta")
    }

    bind[RuleParamMeta] declare { e =>
      e.name is length(50)
      e.title is length(80)
      e.description is length(200)
    }

    bind[Rule] declare { e =>
      e.params is depends("rule")
    }

    bind[RuleParam] declare { e =>
      e.contents is length(500)
    }
  }
}
