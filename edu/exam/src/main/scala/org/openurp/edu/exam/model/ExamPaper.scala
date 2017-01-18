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
package org.openurp.edu.exam.model

import scala.collection.mutable.Buffer

import org.beangle.commons.model.{ Coded, LongId, Named, Remark }
import org.openurp.code.edu.model.ClassroomType
import org.openurp.edu.base.code.model.ExamType
import org.openurp.edu.base.model.{ Course, Project }

class ExamPaper extends LongId with Coded with Named with Remark {

  var project: Project = _

  var examType: ExamType = _

  var courses: Buffer[Course] = _

  var roomType: Option[ClassroomType] = None

  var duration: Short = _

}