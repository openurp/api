/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.edu.room.model

import org.beangle.commons.lang.time.{HourMinute, WeekTime}

import java.time.LocalDate
import scala.collection.mutable

object CycleTime {
  /** 天 */
  val DAY = 1
  /** 周 */
  val WEEK = 2
  /** 月 */
  val MONTH = 4

  def apply(day: LocalDate, beginAt: HourMinute, endAt: HourMinute): CycleTime = {
    val cd = new CycleTime
    cd.beginOn = day
    cd.endOn = day
    cd.beginAt = beginAt
    cd.endAt = endAt
    cd.cycleCount = 1
    cd.cycleType = CycleTime.WEEK
    cd
  }
}

class CycleTime extends Cloneable with Serializable {
  /** 开始日期 */
  var beginOn: LocalDate = _
  /** 结束日期 */
  var endOn: LocalDate = _
  /** 开始时间 */
  var beginAt: HourMinute = _
  /** 结束时间 */
  var endAt: HourMinute = _
  /** 单位 */
  var cycleType: Int = _
  /** 单位数量 */
  var cycleCount: Int = _

  def isOneDay: Boolean = {
    this.beginOn == this.endOn
  }

  def getCycleDays: Int = {
    if (cycleType == CycleTime.DAY) cycleCount
    else if (cycleType == CycleTime.MONTH) cycleCount * 30
    else if (cycleType == CycleTime.WEEK) cycleCount * 7
    else throw new RuntimeException("xxx")
  }

  def convert: mutable.Buffer[WeekTime] = {
    val builder = new TimeUnitBuilder(beginAt, endAt)
    builder.addRange(beginOn, endOn, cycleType, cycleCount)
    builder.build
  }

}
