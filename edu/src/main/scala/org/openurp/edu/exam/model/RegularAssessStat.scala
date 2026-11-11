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
import org.beangle.data.model.pojo.Updatable
import org.openurp.code.edu.model.AttendanceMode
import org.openurp.edu.clazz.model.Clazz

import java.time.Instant
import scala.compiletime.uninitialized

/**
 * 日常考核评估统计
 */
class RegularAssessStat extends LongId, Updatable {
  var clazz: Clazz = uninitialized
  var attendanceMode: AttendanceMode = uninitialized
  var disqualifiedCount: Int = uninitialized
  var assignmentExceedCount: Int = uninitialized
  var absenceExceedCount: Int = uninitialized
  var collegeReviewed: Option[Boolean] = None

  /** 教师签名存储路径 */
  var teacherSignaturePath: Option[String] = None

  /** 教师提交名单时间 */
  var teacherSubmittedAt: Option[Instant] = None

  def this(clazz: Clazz) = {
    this()
    this.clazz = clazz
  }
}
