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

package org.openurp.qos.evaluation.clazz.model

import org.beangle.data.model.LongId
import org.openurp.base.edu.model.{Semester, Teacher}

class FinalTeacherScore extends LongId {
  var teacher: Teacher = _
  var semester: Semester = _
  var stdScore: Float = _
  var supviScore: Float = _
  var departScore: Float = _
  var score: Float = _

  /**部门排名*/
  var departRank: Int = _

  /**全校排名*/
  var schoolRank: Int = _
}
