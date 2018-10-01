/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.course.model

import scala.collection.mutable.{ Buffer, ListBuffer }

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.edu.base.model.Semester
import org.openurp.edu.base.ProjectBased
import org.openurp.edu.base.code.model.{ CourseTakeType, CourseType, ExamMode, GradeType, GradingMode }
import org.openurp.edu.base.model.{ Course, Student }
import org.openurp.edu.grade.model.Grade
import org.openurp.edu.course.model.Clazz

/**
 * 课程成绩
 * </p>
 * 学生因上课取得的成绩，业务唯一主键为[学生、项目、培养类型、学期、课程]。
 * </p>
 * 课程成绩由多个考试成绩组成，一般为平时、期末、补考、缓考、总评等成绩成分。
 *
 * @depend - - - Lesson
 * @depend - - - Course
 * @depend - - - CourseType
 * @depend - - - CourseTakeType
 * @composed 1 has * ExamGrade
 * @depend - - - Project
 * @depend - - - Education
 * @author chaostone
 * @since 2006
 */

class CourseGrade extends LongId with ProjectBased with Grade with Remark {
  /**
   * 设置学生
   */
  var std: Student = _
  /**
   * 设置课程
   */
  var course: Course = _
  /**
   * 获得修读类别
   */
  var courseTakeType: CourseTakeType = _
  /**
   * 学期
   */
  var semester: Semester = _
  /**
   * 任务序号
   */
  var crn: Option[String] = None
  /**
   * 课程类别
   */
  var courseType: CourseType = _
  /**
   * 设置绩点
   */
  var gp: Option[Float] = None

  /**是否免听*/
  var freeListening: Boolean = false
  /**
   * 总评成绩
   */
  var gaGrades: Buffer[GaGrade] = new ListBuffer[GaGrade]
  /**
   * 考核成绩
   */
  var examGrades: Buffer[ExamGrade] = new ListBuffer[ExamGrade]
  /**
   * 得到指定的考试成绩
   */
  def getGrade(gradeType: GradeType): Grade = {
    if (gradeType.isGa) gaGrades.find(eg => eg.gradeType == gradeType).orNull
    else examGrades.find(eg => eg.gradeType == gradeType).orNull
  }

  def getGrade(gradeTypeId: Integer): Grade = {
    getGrade(new GradeType(gradeTypeId))
  }
  /**
   * 考核方式
   */
  var examMode: ExamMode = _

  var score: Option[Float] = None

  var scoreText: String = _

  var passed: Boolean = _

  var status: Int = _

  var gradingMode: GradingMode = _

  var operator: String = _

  var clazz: Option[Clazz] = None

  def gradeType: GradeType = {
    new GradeType(GradeType.Final)
  }
  // 大的成绩放前面
  override def compare(grade: Grade): Int = {
    if (None == score) return 1
    else if (None == grade.score) return -1
    return grade.score.get.compareTo(score.get)
  }
}
