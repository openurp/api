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

import java.time.{DayOfWeek, LocalDate}
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

  def build(beginOn: LocalDate, endOn: LocalDate, dayInterval: Int): Seq[WeekTime] = {
    val timeMap = Collections.newMap[LocalDate, WeekTime]
    var newBeginOn = beginOn
    while (!newBeginOn.isAfter(endOn)) {
      val t = WeekTime.of(newBeginOn)
      timeMap.get(t.startOn) match {
        case Some(existed) => existed.weekstate = existed.weekstate | t.weekstate
        case None => timeMap.put(t.startOn, t)
      }
      newBeginOn = newBeginOn.plusDays(dayInterval)
    }
    val times = timeMap.values.toSeq.sortBy(x => x.startOn)
    times
  }

  def on(semester: Semester): WeekTimeBuilder = {
    new WeekTimeBuilder(semester.beginOn, semester.calendar.firstWeekday)
  }

  def digest(time: WeekTime, semester: Semester): String = {
    if (null == time) return ""
    val dayofWeek = DayOfWeek.of(time.weekday.id)
    val firstDay = DayOfWeek.of(semester.calendar.firstWeekday.id)
    val beginOn = toDay(firstDay, semester.beginOn, dayofWeek)
    val timeBeginOn = time.startOn
    val weeksDistance = Weeks.between(beginOn, timeBeginOn)
    var weekstate = time.weekstate.value
    if (weeksDistance < 0) weekstate >>= (0 - weeksDistance)
    else weekstate <<= weeksDistance

    val weekIndecies = new WeekState(weekstate).weeks.toArray
    val seqs = NumSeqParser.digest(weekIndecies).map { s =>
      if (s.step == 1) {
        if (s.start == s.end) s.start.toString
        else s"${s.start}-${s.end}"
      } else if (s.step == 2) {
        if (s.start % 2 == 1) s"${s.start}-${s.end}单"
        else s"${s.start}-${s.end}双"
      } else {
        s.toString
      }
    }
    seqs.mkString(" ")
  }

  /**
   * 合并相邻或者重叠的时间段<br>
   * 前提条件是待合并的
   *
   * @param tobeMerged
   * @return
   */
  def mergeTimes(tobeMerged: mutable.Buffer[WeekTime], minGap: Int): mutable.Buffer[WeekTime] = {
    if (tobeMerged.isEmpty) return tobeMerged
    val mergedTimeUnits = Collections.newBuffer[WeekTime]
    val activityIter = tobeMerged.iterator
    var toMerged = activityIter.next()
    mergedTimeUnits.+=(toMerged)
    while (activityIter.hasNext) {
      val unit = activityIter.next()
      if (toMerged.mergeable(unit, minGap)) toMerged.merge(unit, minGap)
      else {
        toMerged = unit
        mergedTimeUnits += toMerged
      }
    }
    mergedTimeUnits
  }

  def collect(semester: Semester, dates: Iterable[LocalDate]): WeekState = {
    val dayofWeek = DayOfWeek.of(semester.calendar.firstWeekday.id)
    val semesterFirstday = toDay(dayofWeek, semester.beginOn, dayofWeek)
    val weeks = new mutable.HashSet[Int]
    for (date <- dates) {
      val oneday = toDay(dayofWeek, date, dayofWeek)
      val weekIdx = 1 + Weeks.between(semesterFirstday, oneday)
      weeks.add(weekIdx)
    }
    WeekState.of(weeks)
  }

  private def toDay(firstDay: DayOfWeek, date: LocalDate, day: DayOfWeek) = {
    if (date.getDayOfWeek eq day) date
    else {
      if (firstDay eq DayOfWeek.MONDAY) {
        date.plusDays(day.getValue - date.getDayOfWeek.getValue)
      } else if (firstDay eq DayOfWeek.SUNDAY) {
        if day eq DayOfWeek.SUNDAY then date.plusDays(day.getValue - date.getDayOfWeek.getValue - 7)
        else date.plusDays(day.getValue - date.getDayOfWeek.getValue)
      }
      else throw new RuntimeException("Cannot accept first day " + firstDay)
    }
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
