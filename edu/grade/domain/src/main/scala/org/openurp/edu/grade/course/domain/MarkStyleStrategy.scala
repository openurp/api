/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.course.domain

import org.openurp.edu.grade.code.model.GradeType
import org.openurp.edu.grade.course.model.CourseGradeState

/**
 * 课程成绩记录方式配置策略
 *
 * @author chaostone
 */
trait MarkStyleStrategy {

  /**
   * 针对空白的记录方式进行设置默认值
   */
  def configMarkStyle(gradeState: CourseGradeState, gradeTypes: Seq[GradeType]): Unit
}