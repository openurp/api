/*
 * Copyright (C) 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openurp.base.std.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{DateRange, Updated}
import org.openurp.base.model.ExternSchool
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{EduCategory, EducationLevel}

/**
 * 外部学习经历
 */
class ExternStudent extends LongId with Updated with DateRange {
  var std: Student = null
  var school: ExternSchool = null
  var majorName: Option[String] = None
  var level: EducationLevel = null
  var category: EduCategory = null
}
