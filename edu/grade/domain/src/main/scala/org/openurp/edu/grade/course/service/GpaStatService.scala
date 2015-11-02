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
package org.openurp.edu.grade.course.service

import org.openurp.base.model.Semester
import org.openurp.edu.base.model.Student
import org.openurp.edu.grade.course.model.{ CourseGrade, StdGpa }

/**
 * 平均绩点统计服务
 *
 * @author chaostone
 *
 */
trait GpaStatService {

  /**
   * 如果semesters不包含元素或者为null则统计所有学期 否则统计学生的在校semesters所包含的学期的平均绩点
   *
   * <pre>
   *      平均绩点为： gpa=(∑(绩点*学分))/∑(学分)
   *      平均分为： ga=(∑(得分*学分))/∑(学分)
   * </pre>
   *
   * @param std
   * @return
   */
  def statGpa(std: Student, semesters: Semester*): StdGpa

  /**
   * 给定成绩统计平均绩点
   *
   * @param std
   * @param grades
   * @return
   */
  def statGpa(std: Student, grades: Iterable[CourseGrade]): StdGpa

  /**
   * 统计多个学生的平均绩点和其他信息 如果semesters不包含元素或者为null则统计这些所有学期
   * 否则统计这些学生的semesters所包含的学期的平均绩点
   *
   * @param stds
   * @param education
   * @return
   */
  def statGpas(stds: Iterable[Student], semesters: Semester*): MultiStdGpa
}

/**
 * 多个学生的绩点汇总
 *
 * @author chaostone
 *
 */
class MultiStdGpa(val unit: Any, val stdGpas: Iterable[StdGpa]) {

  val semesters: List[Semester] = statSemesters(stdGpas)

  def statSemesters(stdGpas: Iterable[StdGpa]): List[Semester] = {
    val semesters = new collection.mutable.HashSet[Semester]
    for (stdGp <- stdGpas; stdSemesterGpa <- stdGp.semesterGpas) {
      semesters += stdSemesterGpa.semester
    }
    semesters.toList
  }
}
