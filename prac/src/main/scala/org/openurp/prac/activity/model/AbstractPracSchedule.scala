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
import org.beangle.commons.lang.reflect.Reflections
import org.beangle.commons.lang.time.WeekTime
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{DateRange, Remark}
import org.openurp.base.model.User
import org.openurp.code.edu.model.TeachingMethod

import scala.collection.mutable

/** 实践课程安排
 */
abstract class AbstractPracSchedule extends LongId, DateRange, Remark {
  var weekTime: WeekTime = _
  /** 主题 */
  var topic: Option[String] = None
  /** 日期时间 */
  var times: Option[String] = None
  /** 地点 */
  var places: String = _
  /** 教学方法 */
  var teachingMethod: TeachingMethod = _
  /** 授课教师 */
  var teachers: mutable.Buffer[User] = Collections.newBuffer[User]
  /** 外校教师 */
  var externTeacher: Option[String] = None

  def mergeWith(ns: AbstractPracSchedule): Unit = {
    val nwt = ns.weekTime
    if nwt.firstDay isBefore (beginOn) then
      this.beginOn = nwt.firstDay
    if nwt.lastDay isAfter (endOn) then
      this.endOn = nwt.lastDay
    this.weekTime.weekstate |= nwt.weekstate
  }

  def copyOn(newTime: WeekTime): AbstractPracSchedule = {
    val newer = Reflections.newInstance(getClass())
    newer.topic = this.topic
    newer.updateTime(newTime)
    newer.times = this.times
    newer.places = this.places
    newer.teachingMethod = this.teachingMethod
    newer.teachers ++= this.teachers
    newer.externTeacher = this.externTeacher
    newer
  }

  def updateTime(newTime: WeekTime): Unit = {
    this.weekTime = newTime
    this.beginOn = newTime.firstDay
    this.endOn = newTime.lastDay
  }
}
