/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright (c) 2005, The OpenURP Software.
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
package org.openurp.edu.grade.moral.model

import org.beangle.data.model.LongId
import org.openurp.code.edu.model.GradingMode
import org.openurp.edu.base.model.Semester
import org.openurp.edu.base.model.Student

/**
 * 德育成绩
 */
class MoralGrade extends LongId  {

  var std: Student=_
  var semester: Semester=_
  var score: Option[Float]=None
  var scoreText: String=_
  var passed: Boolean=_
  var status: Int=_
  var gradingMode: GradingMode=_
  var operator: String=_
}
