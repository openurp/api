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
package org.openurp.edu.teach.grade.course.service

import org.openurp.base.model.Semester
import org.openurp.edu.base.model.Student
import org.openurp.edu.teach.grade.course.model.CourseGrade

/**
 * 平均绩点计算服务
 *
 * @author chaostone
 */
trait GpaService {

  /**
   * 统计学生的在校所有学期的平均绩点
   *
   * <pre>
   *      平均绩点为： gpa=(∑(绩点*学分))/∑(学分)
   *      平均分为： ga=(∑(得分*学分))/∑(学分)
   * </pre>
   */
  def calcGpa(std: Student): java.lang.Float

  /**
   * 统计学生的平均绩点<br>
   * 平均绩点为： gpa=(∑(绩点*学分))/∑(学分) 平均绩点以截断的方式保留后面两位
   */
  def calcGpa(std: Student, grades: Iterable[CourseGrade]): java.lang.Float

  /**
   * 统计学生的平均绩点<br>
   * 除"学生"之外的其他参数均为可选参数。<br>
   * 平均绩点为： gpa=(∑(绩点*学分))/∑(学分) 平均绩点以截断的方式保留后面两位
   *
   * @param semester    可以为null
   */
  def calcGpa(std: Student, semester: Semester): java.lang.Float
}
