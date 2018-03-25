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
package org.openurp.edu.exam.model

import scala.collection.mutable.Buffer

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{ Coded, Named, Remark }
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
