/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.award.results.model

import org.beangle.data.model.LongId
import org.openurp.edu.base.model.Student
import org.openurp.base.model.Semester
import org.openurp.edu.award.code.model.SubsidyLevel
import org.openurp.edu.award.code.model.SubsidyCategory

class SubsidyAward extends LongId  {

  /**困难补助种类*/
  var subsidyCategory: SubsidyCategory = _

  /**学生*/
  var std: Student = _

  /**获奖等级*/
  var subsidyLevel: SubsidyLevel = _

  /**评定学期*/
  var semester: Semester = _

  /**金额*/
  var awardAmount: Float = _

  /**是否审核通过*/
  var beApproved: Boolean = _

}