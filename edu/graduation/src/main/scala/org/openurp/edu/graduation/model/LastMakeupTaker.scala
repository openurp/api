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
package org.openurp.edu.graduation.model

import org.beangle.data.model.LongId
import org.openurp.edu.base.code.model.CourseType
import org.openurp.edu.base.model.Student

/**
 * 毕业清考名单
 */
class LastMakeupTaker extends LongId {

  /**清考任务*/
  var clazz: LastMakeupClazz = _

  /** 学生 */
  var std: Student = _

  /** 课程类型 */
  var courseType: CourseType = _

  def this(clazz: LastMakeupClazz, std: Student, courseType: CourseType) {
    this()
    this.clazz = clazz
    this.std = std
    this.courseType = courseType
  }
}
