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

package org.openurp.qos.evaluation.clazz.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.edu.model.Course
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.{Department, Semester}
import org.openurp.base.std.model.Student

/** 期末文字评价
 *
 */
class FinalComment extends LongId, Updated {

  var std: Student = _

  /** 课程序号 */
  var crn: String = _

  /** 教学日历 */
  var semester: Semester = _

  /** 任课教师 */
  var teacher: Teacher = _

  /** 开课院系 */
  var teachDepart: Department = _

  /** 课程 */
  var course: Course = _

  /** 评价等级 */
  var grade: String = _

  /** 内容 */
  var contents: String = _
}
