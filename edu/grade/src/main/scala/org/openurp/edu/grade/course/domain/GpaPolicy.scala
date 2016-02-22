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

import org.openurp.edu.grade.course.model.CourseGrade

/**
 * 平均绩点计算策略
 *
 * @author chaostone
 *
 */
trait GpaPolicy {

  /**
   * 计算平均绩点
   */
  def calcGpa(grades: Iterable[CourseGrade]): java.lang.Float

  /**
   * 计算平均分
   */
  def calcGa(grades: Iterable[CourseGrade]): java.lang.Float

  /**
   * 保留小数位
   */
  def round(gpa: java.lang.Float): java.lang.Float

  /**
   * 平均绩点和平均分的小数点后精确位数,默认为2
   */
  def precision: Int
}
