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
package org.openurp.sin.harvest.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[AssumeRole].declare { e =>
      e.code is length(10)
      e.name is length(80)
    }
    bind[HarvestType].declare { e =>
      e.code is length(10)
      e.name is length(80)
    }
    bind[Literature].declare { e =>
      e.name is length(300)
      e.introduction is length(2000)
      e.isbn is length(100)
    }
    bind[LiteratureMember].declare { e =>
      e.name is length(80)
    }
    bind[PublishedRange].declare { e =>
      e.code is length(10)
      e.name is length(80)
    }

    bind[PublishedSituation].declare { e =>
      e.name is length(300)
      e.enName is length(200)
      e.isbn & e.issn & e.cn are length(100)
    }

    bind[Researcher]
    bind[ThesisHarvest].declare { e =>
      e.name is length(300)
      e.summaries is length(2000)
    }
  }

}
