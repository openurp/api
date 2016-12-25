/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2016, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.base.model

import java.util.GregorianCalendar

import scala.collection.mutable.Buffer

import org.beangle.commons.lang.time.WeekDay.WeekDay
import org.beangle.commons.model.{ Coded, IntId, Named, Remark, TemporalOn, Updated }

/**
 * 教学日历方案
 * 校历（日历方案）记录了一整套学年学期的设置，是连贯性学年学期设置的集合，也可称日历方案。
 */
class Calendar extends IntId with Coded with Named with TemporalOn with Updated {

  var school: School = _

  var semesters: Buffer[Semester] = new collection.mutable.ListBuffer[Semester]

  /**一周中的第一天是周几 */
  var firstWeekday: WeekDay = _
}
/**
 * 学年学期 </p> 代表的是具体学年度的 学期设置，每个学期的起始日期（起始日期beginOn第一天）和结束日期。
 */
class Semester extends IntId with Coded with Named with TemporalOn with Remark {

  /**日历*/
  var calendar: Calendar = _

  /**学年度,一般为yyyy-yyyy或者yyyy的格式*/
  var schoolYear: String = _

  def startWeek(): Int = {
    val gc = new GregorianCalendar();
    gc.setFirstDayOfWeek(calendar.firstWeekday.index)
    gc.setTime(beginOn);
    gc.get(java.util.Calendar.WEEK_OF_YEAR);
  }

  def this(id: Integer, code: String, schoolYear: String, name: String) {
    this()
    this.id = id
    this.code = code
    this.schoolYear = schoolYear
    this.name = name
  }

}
