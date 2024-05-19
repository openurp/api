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

package org.openurp.edu.clazz.model

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.{Component, LongId}
import org.openurp.code.asset.model.ClassroomType
import org.openurp.code.edu.model.{ExamForm, ExamMode}

import java.time.LocalDate

/**
 * 期末考试安排
 * (排考用户指定）
 */
class ClazzFinalExam extends LongId {

  /** 教学任务 */
  var clazz: Clazz = _

  /** 考试日期 */
  var examOn: Option[LocalDate] = None

  /** 开始时间 */
  var beginAt: HourMinute = HourMinute.Zero

  /** 结束时间 */
  var endAt: HourMinute = HourMinute.Zero

  /** 考试方式 */
  var examForm: Option[ExamForm] = None

  /** 考试时长 */
  var examDuration: Short = _

  /** 考试教室类型 */
  var roomType: Option[ClassroomType] = None

}
