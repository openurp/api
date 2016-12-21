/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.student.model

import org.beangle.data.model.LongId
import org.openurp.code.geo.model.Division
import org.openurp.code.edu.model.EnrollMode
import org.openurp.edu.base.model.Student
import org.openurp.code.person.model.HouseholdType

/**
 * 录取信息
 */
class StdAdmission extends LongId {

  /** 学生 */
  var std: Student = _

  /** 录取通知书号 */
  var letterNo:String =_

  /** 录取第几志愿 */
  var admissionIndex:Int=_

  /** 准考证号 */
  var ticketNo: String = _

  /** 入学方式 */
  var enrollMode: EnrollMode = _

  /** 报考省市 */
  var province: String = _

  /** 是否预科生 */
  var preparatory: Boolean = _

  /** 入学前户口所在区划 */
  var formerDivision: Division = _

  /** 入学后户口所在区划 */
  var currentDivision: Division = _

  /** 原户口性质 */
  var householdType:HouseholdType = _
}
