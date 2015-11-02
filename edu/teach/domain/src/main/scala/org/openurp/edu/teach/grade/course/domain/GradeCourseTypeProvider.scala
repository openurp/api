/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.teach.grade.course.domain

import org.openurp.edu.base.code.model.CourseType
import org.openurp.edu.base.model.Student
import org.openurp.edu.base.model.Course

/**
 * 判断学生修读课程的课程类别
 *
 * @author chaostone
 */
trait GradeCourseTypeProvider {

  def getCourseType(std: Student, course: Course, defaultCourseType: CourseType): CourseType
}
