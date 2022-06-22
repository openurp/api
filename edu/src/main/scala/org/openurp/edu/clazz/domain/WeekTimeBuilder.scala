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

package org.openurp.edu.clazz.domain

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.time.{WeekDay, WeekState, WeekTime, Weeks}
import org.beangle.commons.lang.{Numbers, Strings}
import org.openurp.base.model.Semester

import java.time.LocalDate
import scala.collection.mutable

object WeekTimeBuilder {

  def build(semester: Semester, weekSpan: String): Iterable[WeekTime] = {
    var startWeek: Int = 0
    var endWeek: Int = 0
    if (weekSpan.contains("-")) {
      startWeek = Integer.valueOf(Strings.substringBefore(weekSpan, "-"))
      endWeek = Integer.valueOf(Strings.substringAfter(weekSpan, "-"))
    } else if ("*" == weekSpan) {
      startWeek = 1
      endWeek = semester.weeks
    } else if (Numbers.isDigits(weekSpan)) {
      startWeek = Integer.valueOf(weekSpan)
      endWeek = startWeek
    }
    if (0 == startWeek) startWeek = 1
    if (0 == endWeek) endWeek = startWeek
    if (startWeek < 1) startWeek = 1
    if (endWeek > semester.weeks) endWeek = semester.weeks
    val weektimes = new mutable.ArrayBuffer[WeekTime]
    for (wd <- WeekDay.values) {
      weektimes.addAll(on(semester).build(wd, Range(startWeek, endWeek + 1)))
    }
    weektimes
  }

  def on(semester: Semester): WeekTimeBuilder = {
    new WeekTimeBuilder(semester.beginOn, semester.calendar.firstWeekday)
  }
}

class WeekTimeBuilder(val startOn: LocalDate, firstDay: WeekDay) {

  private val firstWeekEndOn = calcFirstWeekEndOn(startOn, firstDay)

  def build(weekday: WeekDay, weeks: Iterable[Int]): Iterable[WeekTime] = {
    val times = Collections.newMap[Int, WeekTime]
    var startDate: LocalDate = startOn
    while (startDate.getDayOfWeek.getValue != weekday.id) {
      startDate = startDate.plusDays(1)
    }
    val minWeek = if startDate.isAfter(firstWeekEndOn) then 2 else 1
    for (week <- weeks; if week >= minWeek) {
      val oneday = startDate.plusWeeks(week - 1)
      val year = oneday.getYear
      val yearStartOn = WeekTime.getStartOn(year, weekday)
      val weektime =
        times.get(year) match {
          case None =>
            val wt = new WeekTime
            times.put(year, wt)
            wt.startOn = yearStartOn
            wt.weekstate = WeekState.Zero
            wt
          case Some(t) => t
        }
      weektime.weekstate |= WeekState.of(Weeks.between(yearStartOn, oneday) + 1)
    }
    times.values
  }

  private def calcFirstWeekEndOn(startOn: LocalDate, firstDay: WeekDay): LocalDate = {
    var endOn = startOn
    val weekendDay = firstDay.previous
    while (endOn.getDayOfWeek.getValue != weekendDay.id) endOn = endOn.plusDays(1)
    endOn
  }

}