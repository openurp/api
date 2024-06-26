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
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.edu.model.Course
import org.openurp.base.std.model.Student

import java.time.Instant

/** 学生个人免修课程
 */
class StdExemptCourse extends LongId, Remark, Updated {
  /** 学生 */
  var std: Student = _
  /** 免修课程 */
  var course: Course = _

  def this(std: Student, course: Course) = {
    this()
    this.std = std
    this.course = course
    this.updatedAt = Instant.now
  }
}
