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

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.archive
import org.beangle.data.model.pojo.Remark
import org.openurp.base.model.{ArchivedByYear, Semester}
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{ExamStatus, ExamType}
import org.openurp.edu.clazz.model.Clazz
import org.openurp.edu.exam.model.{ExamActivity, ExamRoom, ExamTaker}
import scala.compiletime.uninitialized

/** 归档应考学生
 */
@archive
class HisExamTaker extends LongId, Remark, ArchivedByYear {
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

  def convert(): ExamTaker = {
    val t = new ExamTaker
    t.id = this.id
    t.std = this.std
    t.clazz = this.clazz
    t.semester = this.semester
    t.examRoom = this.examRoom
    t.examType = this.examType
    t.examStatus = this.examStatus
    t.activity = this.activity
    t.seatNo = this.seatNo
    t.remark = this.remark
    t
  }
}
