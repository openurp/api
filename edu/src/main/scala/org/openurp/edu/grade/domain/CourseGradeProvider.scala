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

package org.openurp.edu.grade.domain

import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student
import org.openurp.edu.grade.model.CourseGrade

trait CourseGradeProvider {
  /**
   * 查询学生发布的成绩
   *
   * @param std
   * @param semesters
   * @return
   */
  final def get(std: Student): collection.Seq[CourseGrade] = {
    get(std, List.empty)
  }

  /**
   * 查询学生发布的成绩
   *
   * @param std
   * @param semesters
   * @return
   */
  def get(std: Student, semesters: Iterable[Semester]): collection.Seq[CourseGrade]


  /**
   * 查看学生各个课程的通过状态
   *
   * @param std
   * @return
   */
  def getPassedStatus(std: Student): collection.Map[Long, Boolean]
}
