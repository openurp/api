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
package org.openurp.edu.exam.model

import scala.collection.mutable.Buffer

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.time.HourMinute
import org.beangle.commons.model.LongId
import org.openurp.base.model.Semester
import org.openurp.edu.base.code.model.ExamType
import org.openurp.edu.base.model.Project

class ExamTask extends LongId {
  var project: Project = _

  var semester: Semester = _

  var examType: ExamType = _

  var examPaper: ExamPaper = _

  var examOn: Option[java.sql.Date] = None

  var beginAt: HourMinute = _

  var endAt: HourMinute = _

  var stdCount: Int = _

  var examLessons: Buffer[ExamLesson] = Collections.newBuffer[ExamLesson]
}