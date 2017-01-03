/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2016, OpenURP Software.
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
package org.openurp.edu.extern.grade.model

import org.beangle.commons.model.LongId
import org.openurp.edu.base.code.model.ScoreMarkStyle
import org.openurp.base.model.Semester
import org.openurp.edu.base.code.model.ExamStatus
import org.openurp.edu.extern.code.model.ExternExamSubject
import org.openurp.edu.base.model.Student

class ExternExamGrade extends LongId {

  var std: Student = _

  var score: Option[Float] = _

  var scoreText: String = _

  var passed: Boolean = _

  var subject: ExternExamSubject = _

  var semester: Semester = _

  var examNo: Option[String] = _

  var certificate: Option[String] = _

  var acquireOn: java.sql.Date = _

  var markStyle: ScoreMarkStyle = _

  var examStatus: ExamStatus = _
}
