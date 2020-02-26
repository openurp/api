/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.clazz.model

import java.time.LocalDate

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.Component
import org.openurp.code.edu.model.ClassroomType
import org.openurp.code.edu.model.ExamForm
import org.openurp.code.edu.model.ExamMode

/**
 * 考试安排
 */
class Exam extends Component with Serializable with Cloneable {

  /** 期末考试日期 */
  var examOn: Option[LocalDate] = None

  /** 期末考试开始时间   */
  var beginAt: HourMinute = HourMinute.Zero

  /** 期末考试结束时间   */
  var endAt: HourMinute = HourMinute.Zero

  /** 考核方式 */
  var examMode: ExamMode = _

  /** 考试方式 */
  var examForm: ExamForm = _

  /** 考试时长 */
  var examDuration: Short = _

  /** 考试教室类型 */
  var examRoomType: ClassroomType = _

  /** 是否有补考 */
  var hasMakeup: Boolean = _

}
