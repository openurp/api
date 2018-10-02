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

import java.time.LocalDate

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.code.edu.model.Degree
import org.openurp.code.edu.model.EducationResult
import org.openurp.edu.base.model.Student

class Graduation extends LongId with Updated {

  var std: Student = _

  /** 毕业证书编号（电子注册号） */
  var code: String = _

  /** 毕结业日期 */
  var graduateOn: LocalDate = _

  /** 毕结业情况 */
  var educationResult: EducationResult = _

  /** 学位 */
  var degree: Degree = _

  /** 学位授予日期 */
  var degreeAwardOn: LocalDate = _

  /** 学位证书号 */
  var diplomaNo: String = _

}
