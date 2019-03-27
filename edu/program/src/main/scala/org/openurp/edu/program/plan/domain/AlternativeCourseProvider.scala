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
package org.openurp.edu.program.plan.domain

import org.openurp.edu.program.plan.model.MajorAlternativeCourse
import org.openurp.edu.program.plan.model.StdAlternativeCourse
import org.openurp.edu.base.model.Student
import org.openurp.edu.program.plan.model.AlternativeCourse

trait AlternativeCourseProvider {
  /**
   * 得到该学生指定专业类型的所有的替代课程
   *
   * @param std
   * @return list<AlternativeCourse>
   */
  def getAlternatives(std: Student): Seq[AlternativeCourse]

  /**
   * 得到该学生指定专业类型的个人替代课程
   *
   * @param std
   * @return list<AlternativeCourse>
   */
  def getMajorAlternatives(std: Student): Seq[MajorAlternativeCourse]

  /**
   * 得到该学生指定专业类型的专业替代课程
   *
   * @param std
   * @return list<AlternativeCourse>
   */
  def getStdAlternatives(std: Student): Seq[StdAlternativeCourse]
}
