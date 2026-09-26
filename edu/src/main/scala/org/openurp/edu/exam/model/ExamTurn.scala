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

import java.time.LocalDate

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.LongId
import scala.compiletime.uninitialized

/***
 * 排考场次
 */
class ExamTurn extends LongId {

  /**排考组*/
  var group: ExamGroup = uninitialized

  /**考试日期*/
  var examOn: LocalDate = uninitialized

  /**开始时间*/
  var beginAt: HourMinute = uninitialized

  /**结束时间*/
  var endAt: HourMinute = uninitialized

  /**最大容量*/
  var capacity: Option[Int] = None
}
