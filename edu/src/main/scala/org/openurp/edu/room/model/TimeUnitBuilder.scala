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
package org.openurp.edu.room.model

import java.time.LocalDate
import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.time.{HourMinute, WeekTime}

import scala.collection.mutable

class TimeUnitBuilder {

  var times = Collections.newBuffer[WeekTime]

  var beginAt: HourMinute = _

  var endAt: HourMinute = _

  def this(beginAt: HourMinute, endAt: HourMinute) {
    this()
    this.beginAt = beginAt
    this.endAt = endAt
  }

  def build: mutable.Buffer[WeekTime] = times

  /**
   * 在TimeUnitBuilder里添加一个日期
   *
   * @param start
   */
  def add(start: LocalDate): Unit = {
    val time = WeekTime.of(start, beginAt, endAt)
    if (times.isEmpty) times += time
    else {
      times.find(t => t.mergeable(time,15)) match {
        case Some(t) => t.merge(time,15)
        case None => times += time
      }
    }
  }

  /**
   * <pre>
   * 添加以start为起点，cycle为单位，count为步进，循环添加日期，直到end为止
   * </pre>
   *
   * @param start
   * @param end
   * @param cycleType
   * @param count
   */
  def addRange(start: LocalDate, end: LocalDate, cycleType: Int, count: Int): Unit = {
    var startOn = start
    while (!startOn.isAfter(end)) {
      add(startOn)
      cycleType match {
        case CycleTime.DAY => startOn = startOn.plusDays(count)
        case CycleTime.WEEK => startOn = startOn.plusWeeks(count)
        case CycleTime.MONTH => startOn = startOn.plusMonths(count)
      }
    }
  }
}
