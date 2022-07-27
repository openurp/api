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

package org.openurp.edu.clazz.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.edu.code.CourseType
import org.openurp.base.edu.model.Course
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{CourseTakeType, ElectionMode}

/**
 * 上课名单
 */
class CourseTaker extends LongId with Updated with Cloneable with Remark {

  /** 教学任务 */
  var clazz: Clazz = _

  /** 课程 */
  var course: Course = _

  /** 学期 */
  var semester: Semester = _

  /** 学生 */
  var std: Student = _

  /** 修读类别 */
  var takeType: CourseTakeType = _

  /** 课程类别 */
  var courseType: CourseType = _

  /** 是否免听 */
  var freeListening: Boolean = false

  /** 是否替代 */
  var alternative: Boolean = false

  /** 选课方式 * */
  var electionMode: ElectionMode = _

  /** 上课小班 */
  var subclazz: Option[Subclazz] = None

}
