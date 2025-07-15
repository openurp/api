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

package org.openurp.base.edu.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.TemporalOn
import org.openurp.base.hr.model.Teacher

/** 课程负责人
 */
class CourseDirector extends LongId, TemporalOn {
  /** 课程 */
  var course: Course = _

  /** 教研室 */
  var office: Option[TeachingOffice] = None

  /** 负责人 */
  var director: Teacher = _

  def this(course: Course) = {
    this()
    this.course = course
  }
}
