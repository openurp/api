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

package org.openurp.edu.course.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.openurp.base.edu.model.{Course, TeachingOffice}
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.{Department, Semester}
import org.openurp.code.edu.model.CourseType

import scala.collection.mutable

/** 课程执教信息
 */
class CourseTask extends LongId {
  /** 课程 */
  var course: Course = _
  /** 开课院系 */
  var department: Department = _
  /** 学年学期 */
  var semester: Semester = _
  /** 课程类型 */
  var courseType: CourseType = _
  /** 任课教师 */
  var teachers: mutable.Set[Teacher] = Collections.newSet[Teacher]
  /** 教研室 */
  var office: Option[TeachingOffice] = None
  /** 负责人 */
  var director: Option[Teacher] = None
  /** 是否需要教学大纲 */
  var syllabusRequired: Boolean = true

  def this(course: Course, department: Department, semester: Semester, courseType: CourseType) = {
    this()
    this.course = course
    this.department = department
    this.semester = semester
    this.courseType = courseType
  }
}
