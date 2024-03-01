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

import org.openurp.base.std.model.Student
import org.openurp.edu.grade.model.CourseGrade
import org.openurp.edu.grade.model.StdGpa

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
  def calcGpa(grades: Iterable[CourseGrade]): BigDecimal

  /**
   * 计算平均分
   *
   * @param grades
   * @return
   */
  def calcGa(grades: Iterable[CourseGrade]): BigDecimal

  /**
   * 计算平均绩点
   *
   * @param grades
   * @return
   */
  def calc(std: Student, grades: Iterable[CourseGrade], calcDetail: Boolean): StdGpa

}
