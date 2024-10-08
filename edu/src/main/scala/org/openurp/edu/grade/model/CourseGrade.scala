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

package org.openurp.edu.grade.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.Objects
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.Course
import org.openurp.base.model.{ProjectBased, Semester}
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.*
import org.openurp.edu.clazz.model.{Clazz, CourseTaker}

import java.time.Instant
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
class CourseGrade extends LongId, ProjectBased, Grade, Remark {
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
  var gaGrades: mutable.Buffer[GaGrade] = Collections.newBuffer[GaGrade]
  /** 考核成绩 */
  var examGrades: mutable.Buffer[ExamGrade] = Collections.newBuffer[ExamGrade]
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

  def credits: Float = {
    course.getCredits(std.level)
  }

  def this(id: Long, std: Student, course: Course, semester: Semester, crn: String) = {
    this()
    this.id = id
    this.std = std
    this.course = course
    this.semester = semester
  }

  // 同一个学生，同一门课程，成绩好的放前面
  override def compare(grade: Grade): Int = {
    Objects.compareBuilder
      .add(this.std.id, grade.std.id)
      .add(this.course.id, grade.asInstanceOf[CourseGrade].course.id)
      .add(grade.score.orNull, this.score.orNull).build()
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
   * 得到指定的考试成绩
   */
  def getGrade(gradeType: GradeType): Option[Grade] = {
    if (gradeType.isGa) gaGrades.find(eg => eg.gradeType == gradeType)
    else examGrades.find(eg => eg.gradeType == gradeType)
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

  /**
   * 添加总评成绩
   *
   * @param gradeType
   * @return
   */
  def addGaGrade(gradeType: GradeType, status: Int): GaGrade = {
    gaGrades.find(eg => eg.gradeType == gradeType) match {
      case Some(ga) =>
        ga.status = status
        ga
      case None =>
        val ga = new GaGrade()
        ga.courseGrade = this
        ga.createdAt = Instant.now
        ga.updatedAt = Instant.now
        ga.gradeType = gradeType
        ga.gradingMode = this.gradingMode
        ga.status = status
        this.gaGrades.addOne(ga)
        ga
    }

  }

  /**
   * 添加考试成绩
   *
   * @param gradeType
   * @return
   */
  def addExamGrade(gradeType: GradeType, gradingMode: GradingMode, examStatus: ExamStatus, status: Int): ExamGrade = {
    examGrades.find(eg => eg.gradeType == gradeType) match {
      case Some(ea) =>
        ea.gradingMode = gradingMode
        ea.examStatus = examStatus
        ea.status = status
        ea
      case None =>
        val ea = new ExamGrade()
        ea.courseGrade = this
        ea.createdAt = Instant.now
        ea.updatedAt = Instant.now
        ea.gradeType = gradeType
        ea.gradingMode = gradingMode
        ea.examStatus = examStatus
        ea.status = status
        this.examGrades.addOne(ea)
        ea
    }
  }

  @transient
  def gradeType: GradeType = new GradeType(GradeType.Final)

  def updateScore(score: Option[Float], scoreText: Option[String], gp: Option[Float], passed: Boolean): CourseGrade = {
    this.score = score
    this.scoreText = scoreText
    this.gp = gp
    this.passed = passed
    this
  }

  def updateScore(score: Float, scoreText: String, gp: Float, passed: Boolean): CourseGrade = {
    this.score = Some(score)
    this.scoreText = Some(scoreText)
    this.gp = Some(gp)
    this.passed = passed
    this
  }
}

object CourseGrade {
  def apply(taker: CourseTaker): CourseGrade = {
    val g = new CourseGrade()
    g.std = taker.std
    val clazz = taker.clazz
    g.clazz = Some(clazz)
    g.crn = clazz.crn
    g.semester = clazz.semester
    g.course = clazz.course
    g.courseType = taker.courseType
    g.courseTakeType = taker.takeType
    g.freeListening = taker.freeListening
    g.createdAt = Instant.now
    g.updatedAt = Instant.now
    g.project = taker.clazz.project
    g
  }
}
