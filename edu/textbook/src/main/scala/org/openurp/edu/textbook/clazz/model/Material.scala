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
package org.openurp.edu.textbook.clazz.model

import java.time.Instant

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.openurp.edu.base.model.Textbook
import org.openurp.edu.course.model.Clazz
import org.openurp.edu.textbook.clazz.MaterialStatus.ASSIGNED
import org.openurp.edu.textbook.clazz.MaterialStatus.Status

/**
 * 教材
 */
class Material extends LongId {

  var clazz: Clazz = _

  var books = Collections.newBuffer[Textbook]

  var references: String = _

  var extra: String = _

  var passed: Boolean = _

  var auditAt: Instant = _

  var remark: String = _

  var reason: String = _

  var status: Status = ASSIGNED
}
