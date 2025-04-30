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

/** 课程默认教材设置
 */
class CourseTextbook extends LongId, TemporalOn {

  /** 课程 */
  var course: Course = _

  /** 教材 */
  var textbook: Textbook = _

  /** 是否必选教材 */
  var required: Boolean = _

  /** 是否默认教材 */
  var recommended: Boolean = _
}
