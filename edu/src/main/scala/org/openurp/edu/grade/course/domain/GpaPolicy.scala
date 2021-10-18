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

package org.openurp.edu.grade.course.domain

import org.openurp.base.edu.model.Student
import org.openurp.edu.grade.course.model.CourseGrade
import org.openurp.edu.grade.course.model.StdGpa

/**
 * 平均绩点计算策略
 */
trait GpaPolicy {

  /**
   * 计算平均绩点
   *
   * @param grades
   * @return
   */
  def calcGpa(grades: Iterable[CourseGrade]): Float

  /**
   * 计算平均分
   *
   * @param grades
   * @return
   */
  def calcGa(grades: Iterable[CourseGrade]): Float

  /**
   * 计算平均绩点
   *
   * @param grades
   * @return
   */
  def calc(std: Student, grades: Iterable[CourseGrade], calcDetail: Boolean): StdGpa

  /**
   * 平均绩点和平均分的小数点后精确位数,默认为2
   *
   * @return
   */
  def precision: Int
}
