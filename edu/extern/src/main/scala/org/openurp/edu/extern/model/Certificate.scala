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
package org.openurp.edu.extern.model

import java.time.LocalDate

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.code
import org.openurp.code.edu.model.ExamStatus
import org.openurp.code.edu.model.GradingMode
import org.openurp.edu.base.model.Student
import org.openurp.edu.extern.code.model.ExamSubject
import org.beangle.commons.collection.Collections
import scala.collection.mutable.Buffer
import org.openurp.edu.grade.course.model.CourseGrade

/**
 * 校外证书成绩
 */
class Certificate extends LongId {

  var code: Option[String] = None

  var std: Student = _

  var score: Option[Float] = None

  var scoreText: String = _

  var passed: Boolean = _

  var subject: ExamSubject = _

  var examNo: Option[String] = None

  var acquiredOn: LocalDate = _

  var gradingMode: GradingMode = _

  var examStatus: ExamStatus = _

  var courseGrades: Buffer[CourseGrade] = Collections.newBuffer[CourseGrade]
}
