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
package org.openurp.edu.student.model

import org.beangle.data.model.LongId
import org.openurp.code.geo.model.Division
import org.openurp.code.edu.model.EnrollMode
import org.openurp.edu.base.model.Student
import org.openurp.code.person.model.HouseholdType
import org.openurp.edu.base.model.Major
import org.openurp.base.model.Department
import java.time.LocalDate
import org.openurp.code.edu.model.EducationMode

/**
 * 录取信息
 */
class Admission extends LongId {

  /** 学生 */
  var std: Student = _

  /** 录取年月*/
  var enrollOn: LocalDate = _

  /** 录取专业*/
  var major: Major = _

  /** 录取院系*/
  var department: Department = _

  /** 录取通知书号 */
  var letterNo: Option[String] = None

  /** 录取第几志愿 */
  var admissionIndex: Option[Int] = None

  /** 入学方式 */
  var enrollMode: EnrollMode = _

  /** 培养方式*/
  var educationMode: EducationMode = _
}
