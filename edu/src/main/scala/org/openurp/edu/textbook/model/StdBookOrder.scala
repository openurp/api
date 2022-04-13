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

package org.openurp.edu.textbook.model

import org.beangle.data.model.LongId
import org.beangle.data.model.LongId
import org.openurp.base.edu.model.Student
import org.openurp.base.edu.model.Course
import org.openurp.base.edu.model.Textbook
import org.openurp.base.edu.model.Semester
import org.openurp.base.model.Department
import java.time.Instant
import org.openurp.base.edu.model.Project
import org.openurp.base.model.Department

/**
 * 学生教材订单
 *
 */
class StdBookOrder extends LongId {
  /**项目*/
  var project: Project = _

  /**学年学期*/
  var semester: Semester = _

  /**学生*/
  var std: Student = _

  /**课程*/
  var course: Course = _

  /**课程序号*/
  var crn: String = _

  /**教材*/
  var textbook: Textbook = _

  /**开课院系*/
  var teachDepart: Department = _

  /**教师姓名*/
  var teacherNames: Option[String] = None

  /**创建时间*/
  var createdAt: Instant = _

  /**是否退订*/
  var withdrawed: Boolean = _

  /**退订时间*/
  var withdrawAt: Option[Instant] = None
}
