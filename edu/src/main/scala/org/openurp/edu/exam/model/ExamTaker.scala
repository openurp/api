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

package org.openurp.edu.exam.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{ExamStatus, ExamType}
import org.openurp.edu.clazz.model.{Clazz, CourseTaker}
import scala.compiletime.uninitialized

/**
 * 应考学生
 */
class ExamTaker extends LongId, Remark {

  /** 教学任务 */
  var clazz: Clazz = uninitialized

  /** 学年学期 */
  var semester: Semester = uninitialized

  /** 学生 */
  var std: Student = uninitialized

  /** 考场 */
  var examRoom: Option[ExamRoom] = None

  /** 考试类型 */
  var examType: ExamType = uninitialized

  /** 考试活动 */
  var activity: Option[ExamActivity] = None

  /** 考试情况 */
  var examStatus: ExamStatus = uninitialized

  /** 座位号 */
  var seatNo: Short = uninitialized

  def this(taker: CourseTaker, examType: ExamType) = {
    this()
    this.examStatus = new ExamStatus(ExamStatus.Normal)
    this.examType = examType
    this.std = taker.std
    this.clazz = taker.clazz
    this.semester = taker.clazz.semester
  }

  def credits: Float = {
    clazz.course.getCredits(std.level)
  }
}
