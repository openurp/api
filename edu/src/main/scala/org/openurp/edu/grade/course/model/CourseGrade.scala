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

package org.openurp.edu.grade.course.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.code.edu.model.{CourseTakeType, ExamMode, GradeType, GradingMode}
import org.openurp.base.edu.code.model.CourseType
import org.openurp.base.edu.model.Course
import org.openurp.base.model.{ProjectBased, Semester}
import org.openurp.base.std.model.Student
import org.openurp.edu.clazz.model.Clazz
import org.openurp.edu.grade.model.Grade

import scala.collection.mutable

/**
 * 课程成绩
 * </p>
 * 学生因上课取得的成绩，业务唯一主键为[学生、项目、培养类型、学期、课程]。
 * </p>
 * 课程成绩由多个考试成绩组成，一般为平时、期末、补考、缓考、总评等成绩成分。
 *
 * @author chaostone
 * @since 2014
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
  var crn: String = _
  /**
   * 课程类别
   */
  var courseType: CourseType = _
  /**
   * 设置绩点
   */
  var gp: Option[Float] = None

  /** 是否免听 */
  var freeListening: Boolean = false
  /**
   * 总评成绩
   */
  var gaGrades: mutable.Buffer[GaGrade] = Collections.newBuffer[GaGrade]
  /**
   * 考核成绩
   */
  var examGrades: mutable.Buffer[ExamGrade] = Collections.newBuffer[ExamGrade]

  /**
   * 考核方式
   */
  var examMode: ExamMode = _

  var score: Option[Float] = None

  var scoreText: Option[String] = None

  var passed: Boolean = _

  var status: Int = _

  var gradingMode: GradingMode = _

  var operator: Option[String] = None

  var clazz: Option[Clazz] = None

  /**
   * 得到指定的考试成绩
   */
  def getGrade(gradeType: GradeType): Option[Grade] = {
    if (gradeType.isGa) gaGrades.find(eg => eg.gradeType == gradeType)
    else examGrades.find(eg => eg.gradeType == gradeType)
  }

  /**
   * 得到指定的考试成绩
   */
  def getExamGrade(gt: GradeType): Option[ExamGrade] = {
    if (gt.isGa) throw new RuntimeException(s"${gt.id} is not exam grade type")
    examGrades.find(eg => eg.gradeType == gt)
  }

  /**
   * 得到指定的总评成绩
   */
  def getGaGrade(gt: GradeType): Option[GaGrade] = {
    if (!gt.isGa) throw new RuntimeException(s"${gt.id} is not ga grade type")
    gaGrades.find(eg => eg.gradeType == gt)
  }

  def getScoreText(gt: GradeType): Option[String] = {
    getGrade(gt) match {
      case None => None
      case Some(g) => g.scoreText
    }
  }

  /**
   * 添加考试成绩
   */
  def addExamGrade(examGrade: ExamGrade): CourseGrade = {
    examGrades += examGrade
    examGrade.courseGrade = this
    this
  }

  /**
   * 添加总评成绩
   */
  def addGaGrade(gaGrade: GaGrade): CourseGrade = {
    gaGrades += gaGrade
    gaGrade.courseGrade = this
    this
  }

  def gradeType: GradeType = {
    new GradeType(GradeType.Final)
  }

  // 大的成绩放前面
  override def compare(grade: Grade): Int = {
    if (score.isEmpty) return 1
    else if (grade.score.isEmpty) return -1
    grade.score.get.compareTo(score.get)
  }
}
