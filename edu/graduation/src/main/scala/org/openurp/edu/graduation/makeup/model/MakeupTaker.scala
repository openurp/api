/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
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
package org.openurp.edu.graduation.makeup.model

import org.beangle.data.model.LongId
import org.openurp.edu.base.code.model.CourseType
import org.openurp.edu.base.model.Student

/**
 * 毕业清考名单
 */
class MakeupTaker extends LongId {

  /**清考任务*/
  var makeupCourse: MakeupCourse = _

  /** 学生 */
  var std: Student = _

  /** 课程类型 */
  var courseType: CourseType = _

  def this(c: MakeupCourse, std: Student, courseType: CourseType) {
    this()
    this.makeupCourse = c
    this.std = std
    this.courseType = courseType
  }
}
