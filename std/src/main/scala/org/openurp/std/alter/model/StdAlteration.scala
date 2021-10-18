/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.std.alter.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, DateRange, Remark, Updated}
import org.openurp.code.std.model.{StdAlterReason, StdAlterType}
import org.openurp.base.edu.model.{Semester, Student}

import scala.collection.mutable.Buffer

/**
 * 学籍异动
 */
class StdAlteration extends LongId with Coded with Updated with DateRange with Remark {

  var std: Student = _

  var semester: Semester = _

  var alterType: StdAlterType = _

  var reason: Option[StdAlterReason] = None

  var items: Buffer[StdAlterationItem] = Collections.newBuffer[StdAlterationItem]

  var effective: Boolean = _

}
