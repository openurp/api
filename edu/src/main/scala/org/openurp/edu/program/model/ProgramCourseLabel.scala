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

package org.openurp.edu.program.model

import org.beangle.data.model.LongId
import org.openurp.base.edu.model.Course
import org.openurp.code.edu.model.ProgramCourseTag

/** 培养方案课程标签
 */
class ProgramCourseLabel extends LongId {

  var program: Program = _

  var tag: ProgramCourseTag = _

  var course: Course = _

  def this(program: Program, course: Course, tag: ProgramCourseTag) = {
    this()
    this.program = program
    this.course = course
    this.tag = tag
  }
}
