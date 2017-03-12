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
package org.openurp.edu.extern.exam.model

import org.beangle.commons.model.LongId
import org.openurp.edu.base.code.model.ScoreMarkStyle
import org.openurp.base.model.Semester
import org.openurp.edu.base.code.model.ExamStatus
import org.openurp.edu.extern.code.model.ExamSubject
import org.openurp.edu.base.model.Student

class ExamGrade extends LongId {

  var std: Student = _

  var score: Option[Float] = None

  var scoreText: String = _

  var passed: Boolean = _

  var subject: ExamSubject = _

  var semester: Semester = _

  var examNo: Option[String] = None

  var certificate: Option[String] = None

  var acquireOn: java.sql.Date = _

  var markStyle: ScoreMarkStyle = _

  var examStatus: ExamStatus = _
}
