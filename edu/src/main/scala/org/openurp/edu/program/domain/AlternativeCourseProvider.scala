/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.edu.program.domain

import org.openurp.base.edu.model.Student
import org.openurp.edu.program.model.{AlternativeCourse, MajorAlternativeCourse, StdAlternativeCourse}
import org.openurp.edu.program.model.{MajorAlternativeCourse, StdAlternativeCourse}

trait AlternativeCourseProvider {
  /**
   * 得到该学生指定专业类型的所有的替代课程
   *
   * @param std
   * @return list<AlternativeCourse>
   */
  def getAlternatives(std: Student): collection.Seq[AlternativeCourse]

  /**
   * 得到该学生指定专业类型的个人替代课程
   *
   * @param std
   * @return list<AlternativeCourse>
   */
  def getMajorAlternatives(std: Student): collection.Seq[MajorAlternativeCourse]

  /**
   * 得到该学生指定专业类型的专业替代课程
   *
   * @param std
   * @return list<AlternativeCourse>
   */
  def getStdAlternatives(std: Student): collection.Seq[StdAlternativeCourse]
}
