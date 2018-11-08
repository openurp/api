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
package org.openurp.edu.extern.model

import java.time.LocalDate

import org.beangle.data.model.LongId
import org.openurp.code.edu.model.EduCategory
import org.openurp.code.edu.model.EducationLevel
import org.openurp.edu.base.model.Student

/**
 * 校外成绩
 */
class ExternGrade extends LongId {

  var school: ExternSchool = _

  var level: EducationLevel = _

  var category: EduCategory = _

  var majorName: Option[String] = None

  var courseName: String = _

  var credits: Float = _

  var std: Student = _

  var acquiredOn: LocalDate = _

  var scoreText: String = _

  var passed: Boolean = _

  var converted: ConvertedGrade = _
}
