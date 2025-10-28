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

package org.openurp.code

import org.beangle.data.model.pojo.Updated
import org.beangle.data.orm.{IdGenerator, MappingModule}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator(classOf[String], "code")
    defaultCache("openurp-code", "read-write")

    bind[CodeBean].declare { e =>
      e.code.is(length(20), unique)
      e.name is length(100)
      e.enName is length(300)
      e.remark is length(200)
    }.cacheable().generator(IdGenerator.Code)

    bind[Updated].declare { e =>
      e.updatedAt is default("current")
    }
  }

}
