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

package org.openurp.prac.mandarin.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.{AuditStatus, Semester}
import org.openurp.base.std.model.Student

import java.time.YearMonth

/** 学生普通话测试的成绩
 */
class MandarinGrade extends LongId, Updated {

  def this(std: Student) = {
    this()
    this.std = std
  }

  /** 学生 */
  var std: Student = _
  /** 学年学期 */
  var semester: Semester = _
  /** 分数 */
  var score: Float = _
  /** 证书号 */
  var certificateNo: Option[String] = None
  /** 获得年月 */
  var acquiredIn: YearMonth = _
  /** 课程成绩ID */
  var courseGradeId: Option[Long] = None
  /** 是否通过 */
  var passed: Boolean = _
}
