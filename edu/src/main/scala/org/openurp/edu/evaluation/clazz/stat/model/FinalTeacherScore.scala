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
package org.openurp.edu.evaluation.clazz.stat.model

import org.beangle.data.model.LongId
import org.openurp.base.edu.model.Teacher
import org.openurp.base.edu.model.Semester

class FinalTeacherScore extends LongId with Rank {
  var teacher: Teacher = _
  var semester: Semester = _
  var stdScore: Float = _
  var supviScore: Float = _
  var departScore: Float = _
  var score: Float = _

}
