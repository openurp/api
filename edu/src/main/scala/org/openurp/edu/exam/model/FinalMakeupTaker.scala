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

package org.openurp.edu.exam.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.CourseType

import java.time.Instant

/**
 * 毕业清考名单
 */
class FinalMakeupTaker extends LongId, Updated, Remark {

  /** 清考任务 */
  var makeupCourse: FinalMakeupCourse = _

  /** 学生 */
  var std: Student = _

  /** 课程类型 */
  var courseType: CourseType = _

  /** 最好成绩 */
  var scores: String = _

  def this(c: FinalMakeupCourse, std: Student, courseType: CourseType) = {
    this()
    this.makeupCourse = c
    this.std = std
    this.courseType = courseType
    this.updatedAt = Instant.now
  }
}
