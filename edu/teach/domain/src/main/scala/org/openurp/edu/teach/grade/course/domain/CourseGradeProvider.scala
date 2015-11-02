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
package org.openurp.edu.teach.grade.course.domain

import org.openurp.base.model.Semester
import org.openurp.edu.base.model.Student
import org.openurp.edu.teach.grade.course.model.CourseGrade

/**
 * 学生成绩查询<br>
 *
 * @author chaostone
 */
trait CourseGradeProvider {

  /**
   * 查询学生发布的成绩
   */
  def getPublished(std: Student, semesters: Semester*): Seq[CourseGrade]

  /**
   * 查询学生所有成绩
   */
  def getAll(std: Student, semesters: Semester*): Seq[CourseGrade]

  /**
   * 查询一批学生发布的成绩
   */
  def getPublished(stds: Iterable[Student], semesters: Semester*): collection.Map[Student, Seq[CourseGrade]]

  /**
   * 查询一批学生所有成绩
   */
  def getAll(stds: Iterable[Student], semesters: Semester*): collection.Map[Student, Seq[CourseGrade]]

  /**
   * 查看学生各个课程的通过状态
   */
  def getPassedStatus(std: Student): collection.Map[java.lang.Long, Boolean]
}
