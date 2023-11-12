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

package org.openurp.base.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.beta
import org.beangle.commons.lang.time.WeekDay
import org.beangle.data.model.IntId
import org.beangle.data.model.pojo.*

import java.time.temporal.{ChronoUnit, TemporalUnit}
import java.time.{LocalDate, ZoneId}
import java.util.GregorianCalendar
import scala.collection.mutable

/**
 * 教学日历方案
 * 校历（日历方案）记录了一整套学年学期的设置，是连贯性学年学期设置的集合，也可称日历方案。
 */
class Calendar extends IntId with Coded with Named with TemporalOn with Updated {

  var school: School = _

  var semesters: mutable.Buffer[Semester] = new collection.mutable.ListBuffer[Semester]

  /** 一周中的第一天是周几 */
  var firstWeekday: WeekDay = _

  def weekdays: List[WeekDay] = {
    val wds = Collections.newBuffer[WeekDay]
    var first = firstWeekday
    wds.addOne(first)
    (1 to 6) foreach { i =>
      first = first.next
      wds.addOne(first)
    }
    wds.toList
  }
}

/**
 * 学年学期 </p> 代表的是具体学年度的 学期设置，每个学期的起始日期（起始日期beginOn第一天）和结束日期。
 */
class Semester extends IntId with Coded with Named with DateRange with Remark {

  /** 日历 */
  var calendar: Calendar = _

  /** 学年度,一般为yyyy-yyyy或者yyyy的格式 */
  var schoolYear: String = _

  /** 是否已经存档 */
  var archived: Boolean = _

  /** 学期中的阶段 */
  var stages: mutable.Buffer[SemesterStage] = Collections.newBuffer[SemesterStage]

  def startWeek(): Int = {
    val gc = new GregorianCalendar();
    gc.setFirstDayOfWeek(calendar.firstWeekday.index)
    gc.setTime(java.util.Date.from(beginOn.atStartOfDay(ZoneId.systemDefault).toInstant))
    gc.get(java.util.Calendar.WEEK_OF_YEAR);
  }

  def weeks: Int = {
    Math.ceil(beginOn.until(endOn.plusDays(1), ChronoUnit.DAYS) / 7.0).intValue()
  }

  def this(id: Int, code: String, schoolYear: String, name: String) = {
    this()
    this.id = id
    this.code = code
    this.schoolYear = schoolYear
    this.name = name
  }
}

/** 教学日历中的阶段 */
class CalendarStage extends IntId with Named {
  var school: School = _
  var vacation: Boolean = _
}

/** 学期中的阶段 */
class SemesterStage extends IntId with DateRange with Remark {
  var semester: Semester = _
  var stage: CalendarStage = _
}
