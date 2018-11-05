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
package org.openurp.edu.workload.model

import org.beangle.data.model.LongId
import org.openurp.code.edu.model.TeachLangType
import org.openurp.edu.base.code.model.CourseCategory
import org.openurp.edu.base.model.Course
import org.openurp.edu.base.model.Semester
import org.openurp.edu.course.model.Clazz

class TeachWorkload extends LongId {

  var clazz:Clazz = _

  var course: Course = _

  var semester: Semester = _

  var langType: TeachLangType = _

  var courseCategory: CourseCategory = _

  var modulus: Modulus = _

  var stdCount: Integer = _

  var totalWorkload: Integer = _

}
