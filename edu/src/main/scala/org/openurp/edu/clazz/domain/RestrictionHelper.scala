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

package org.openurp.edu.clazz.domain

import org.beangle.commons.lang.Strings
import org.beangle.data.dao.Condition
import org.openurp.edu.clazz.model.RestrictionMeta

object RestrictionHelper {

  def build(meta: RestrictionMeta, alias: String, id: String): Condition = {
    var template = s" alias.meta = ${meta.id} and (case when alias.included=true and locate(:values,','||alias.contents||',')>0 then 1 else 0 end) = 1 "
    val paramName = "metaValue" + randomInt + "s"
    template = Strings.replace(template, "alias", alias)
    template = Strings.replace(template, "values", paramName)
    new Condition(template).param("," + id + ",")
  }

  private def randomInt = {
    var d = String.valueOf(Math.random)
    Strings.replace(d, ".", "").substring(0, 8)
  }
}
