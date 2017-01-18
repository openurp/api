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
package org.openurp.edu.workload.model

import org.beangle.commons.model.LongId
import org.openurp.base.model.Semester
import org.openurp.edu.base.code.model.TeachLangType
import org.openurp.edu.base.code.model.CourseCategory
import org.openurp.edu.base.model.Course
import org.openurp.edu.lesson.model.Lesson

class TeachWorkload extends LongId {

  var lesson: Lesson = _

  var course: Course = _

  var semester: Semester = _

  var langType: TeachLangType = _

  var courseCategory: CourseCategory = _

  var modulus: Modulus = _

  var stdCount: Integer = _

  var totalWorkload: Integer = _

}