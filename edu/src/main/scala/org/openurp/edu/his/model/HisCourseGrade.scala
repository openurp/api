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

package org.openurp.edu.his.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.archive
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.Course
import org.openurp.base.model.{ArchivedByYear, ProjectBased, Semester}
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{CourseTakeType, CourseType, ExamMode, GradeType, GradingMode}
import org.openurp.edu.clazz.model.Clazz
import org.openurp.edu.grade.model.{CourseGrade, Grade}

import scala.collection.mutable

/** 归档课程成绩
 */
@archive
class HisCourseGrade extends LongId, ProjectBased, Grade, Remark, ArchivedByYear {
  /** 设置学生 */
  var std: Student = _
  /** 设置课程 */
  var course: Course = _
  /** 获得修读类别 */
  var courseTakeType: CourseTakeType = _
  /** 学期 */
  var semester: Semester = _
  /** 任务序号 */
  var crn: String = _
  /** 课程类别 */
  var courseType: CourseType = _
  /** 设置绩点 */
  var gp: Option[Float] = None
  /** 是否免听 */
  var freeListening: Boolean = false
  /** 总评成绩 */
  var gaGrades: mutable.Buffer[HisGaGrade] = Collections.newBuffer[HisGaGrade]
  /** 考核成绩 */
  var examGrades: mutable.Buffer[HisExamGrade] = Collections.newBuffer[HisExamGrade]
  /** 考核方式 */
  var examMode: ExamMode = _
  /** 得分 */
  var score: Option[Float] = None
  /** 成绩 */
  var scoreText: Option[String] = None
  /** 是否通过 */
  var passed: Boolean = _
  /** 状态 */
  var status: Int = _
  /** 记录方式 */
  var gradingMode: GradingMode = _
  /** 操作者 */
  var operator: Option[String] = None
  /** 教学班 */
  var clazz: Option[Clazz] = None
  /** 数据来源 */
  var provider: Option[String] = None

  def convert(): CourseGrade = {
    val cg = new CourseGrade
    cg.std = std
    cg.course = course
    cg.courseTakeType = courseTakeType
    cg.semester = semester
    cg.crn = crn
    cg.courseType = courseType
    cg.gp = gp
    cg.freeListening = freeListening
    cg.examMode = examMode
    cg.score = score
    cg.scoreText = scoreText
    cg.passed = passed
    cg.status = status
    cg.gradingMode = gradingMode
    cg.operator = operator
    cg.clazz = clazz
    cg.provider = provider

    cg.gaGrades = gaGrades.map(_.convert())
    cg.examGrades = examGrades.map(_.convert())
    cg
  }

  def credits: Float = {
    if null == std || null == course then 0f else course.getCredits(std.level)
  }

  /** 得到指定的考试成绩
   */
  def getExamGrade(gt: GradeType): Option[HisExamGrade] = {
    if (gt.isGa) throw new RuntimeException(s"${gt.id} is not exam grade type")
    examGrades.find(eg => eg.gradeType == gt)
  }

  /** 得到指定的总评成绩
   */
  def getGaGrade(gt: GradeType): Option[HisGaGrade] = {
    if (!gt.isGa) throw new RuntimeException(s"${gt.id} is not ga grade type")
    gaGrades.find(eg => eg.gradeType == gt)
  }

  def getScoreText(gt: GradeType): Option[String] = {
    getGrade(gt) match {
      case None => None
      case Some(g) => g.scoreText
    }
  }

  /** 得到指定的考试成绩
   */
  def getGrade(gradeType: GradeType): Option[Grade] = {
    if (gradeType.isGa) gaGrades.find(eg => eg.gradeType == gradeType)
    else examGrades.find(eg => eg.gradeType == gradeType)
  }

  @transient
  def gradeType: GradeType = new GradeType(GradeType.Final)
}
