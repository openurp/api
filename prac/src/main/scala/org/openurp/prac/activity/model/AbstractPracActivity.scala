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

package org.openurp.prac.activity.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.time.{HourMinute, WeekTime}
import org.beangle.data.model.LongId
import org.openurp.base.model.{Department, Project, Semester, User}

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.collection.mutable

/** 实践活动
 */
abstract class AbstractPracActivity extends LongId {
  /** 项目 */
  var project: Project = _
  /** 学期 */
  var semester: Semester = _
  /** 开课院系 */
  var department: Department = _
  /** 学分 */
  var credits: Option[Float] = None
  /** 授课教师 */
  var teachers: mutable.Buffer[User] = Collections.newBuffer[User]
  /** 外校教师 */
  var externTeacher: Option[String] = None
  /** 实际人数 */
  var stdCount: Int = _

  /** 会话列表 */
  var schedules: mutable.Buffer[AbstractPracSchedule] = Collections.newBuffer[AbstractPracSchedule]

  def teacherNames: String = {
    teachers.map(_.name).appendAll(externTeacher).mkString(",")
  }

  def scheduleContent: String = {
    sessions.map(x => x.toString).mkString("\r\n")
  }

  def merge(): Unit = {
    val copyed = Collections.newBuffer(schedules)
    copyed foreach { ns =>
      schedules filter (x => x.weekTime.beginAt.value > 0 &&
        x.weekTime.beginAt == ns.weekTime.beginAt &&
        x.weekTime.startOn == ns.weekTime.startOn &&
        x.id != ns.id) foreach { e =>
        if ns.teachers == e.teachers && ns.externTeacher == e.externTeacher && ns.places == e.places then
          e.mergeWith(ns)
          schedules.subtractOne(ns)
      }
    }
  }

  def sessions: Iterable[PracSession] = {
    val map = Collections.newMap[String, PracSession]
    schedules foreach { s =>
      val key =
        if s.weekTime == null then s.beginOn.toString + s.places
        else s.weekTime.beginAt.toString() + s.weekTime.endAt.toString() + s.places
      map.get(key) match
        case Some(e) => if (s.weekTime != null) e.add(s.weekTime)
        case None =>
          val e = new PracSession(s.beginOn, s.endOn)
          e.times = s.times
          e.places = s.places
          map.put(key, e)
          if s.weekTime != null then e.add(s.weekTime)
    }
    map.values.toBuffer.sortBy(_.beginOn)
  }
}

/**
 * 表示一次连续的时间安排，不论时间是否按周分布
 *
 * @param beginOn
 * @param endOn
 */
class PracSession(var beginOn: LocalDate, var endOn: LocalDate) {

  var dates = Collections.newSet[LocalDate]

  var beginAt: HourMinute = _

  var endAt: HourMinute = _

  var times: Option[String] = None

  var places: String = _

  def add(wt: WeekTime): Unit = {
    if (wt.weekstate.value != 0) {
      if wt.firstDay.isBefore(beginOn) then this.beginOn = wt.firstDay
      if wt.lastDay.isAfter(endOn) then this.endOn = wt.lastDay
      dates ++= wt.dates
      beginAt = wt.beginAt
      endAt = wt.endAt
    }
  }

  override def toString: String = {
    val contents = new StringBuilder
    if beginAt != null && beginAt.value > 0 then
      val dates = this.dates.toList.sorted
      var thisMonth = -1
      val pattern1 = DateTimeFormatter.ofPattern("M-d")
      val pattern2 = DateTimeFormatter.ofPattern("d")
      val datesb = dates.map { date =>
        if date.getMonthValue != thisMonth then
          thisMonth = date.getMonthValue
          date.format(pattern1)
        else
          date.format(pattern2)
      }.mkString(",")
      contents.append(datesb)
      contents.append(" ")
      contents.append(beginAt.toString()).append("~").append(endAt.toString)
    else
      val pattern3 = DateTimeFormatter.ofPattern("MM-dd")
      contents.append(beginOn.format(pattern3))
      contents.append("~").append(endOn.format(pattern3))
      times foreach { t =>
        contents.append(" ").append(t)
      }
    end if
    contents.append(" ").append(places)
    contents.mkString
  }
}
