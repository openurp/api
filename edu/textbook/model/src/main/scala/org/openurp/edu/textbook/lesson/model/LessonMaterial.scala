/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
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
package org.openurp.edu.textbook.lesson.model

import java.sql.Date

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.openurp.edu.base.model.Textbook
import org.openurp.edu.teach.lesson.model.Lesson
import org.openurp.edu.textbook.lesson.MaterialStatus.{ASSIGNED, Status}

class LessonMaterial extends LongId {

  var lesson: Lesson = _

  var books = Collections.newBuffer[Textbook]

  var referenceBooks: String = _

  var extra: String = _

  var passed: Boolean = _

  var auditAt: Date = _

  var remark: String = _

  var useExplain: String = _

  var status: Status = ASSIGNED
}
