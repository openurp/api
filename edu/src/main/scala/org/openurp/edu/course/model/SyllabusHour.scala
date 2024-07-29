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

package org.openurp.edu.course.model

import org.beangle.data.model.LongId
import org.openurp.code.edu.model.TeachingNature

/** 课程大纲课时分配
 */
class SyllabusHour extends LongId {
  /** 课程大纲 */
  var syllabus: Syllabus = _
  /** 课时分类 */
  var nature: TeachingNature = _
  /** 学时 */
  var creditHours: Float = _

  def this(syllabus: Syllabus, nature: TeachingNature, creditHours: Float) = {
    this()
    this.syllabus = syllabus
    this.nature = nature
    this.creditHours = creditHours
  }
}
