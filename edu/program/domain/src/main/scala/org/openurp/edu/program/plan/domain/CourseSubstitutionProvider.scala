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
package org.openurp.edu.program.plan.domain

import org.openurp.edu.program.plan.model.MajorCourseSubstitution
import org.openurp.edu.program.plan.model.StdCourseSubstitution
import org.openurp.edu.base.model.Student
import org.openurp.edu.program.plan.model.CourseSubstitution

trait CourseSubstitutionProvider {
  /**
   * 得到该学生指定专业类型的所有的替代课程
   *
   * @param std
   * @return list<CourseSubstitution>
   */
  def getCourseSubstitutions(std: Student): Seq[CourseSubstitution]

  /**
   * 得到该学生指定专业类型的个人替代课程
   *
   * @param std
   * @return list<CourseSubstitution>
   */
  def getMajorCourseSubstitutions(std: Student): Seq[MajorCourseSubstitution]

  /**
   * 得到该学生指定专业类型的专业替代课程
   *
   * @param std
   * @return list<CourseSubstitution>
   */
  def getStdCourseSubstitutions(std: Student): Seq[StdCourseSubstitution]
}
