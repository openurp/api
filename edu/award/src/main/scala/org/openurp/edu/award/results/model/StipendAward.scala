/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.award.results.model

import org.beangle.data.model.LongId
import org.openurp.edu.base.model.Student
import org.openurp.base.model.Semester
import org.openurp.edu.award.code.model.StipendLevel
import org.openurp.edu.award.code.model.StipendCategory

class StipendAward extends LongId {

  /**助学金种类*/
  var stipendCategory: StipendCategory = _

  /**学生*/
  var std: Student = _

  /**获奖等级*/
  var stipendLevel: StipendLevel = _

  /**评定学期*/
  var semester: Semester = _

  /**金额*/
  var awardAmount: Float = _

  /**是否审核通过*/
  var beApproved: Boolean = _

}
