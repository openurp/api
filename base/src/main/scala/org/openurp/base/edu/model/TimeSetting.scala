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

package org.openurp.base.edu.model

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.IntId
import org.beangle.data.model.pojo.{Named, TemporalOn}
import org.openurp.base.model.{Campus, Project}
import org.openurp.code.edu.model.DayPart

import scala.collection.mutable.Buffer
import scala.compiletime.uninitialized

/**
 * 时间设置
 */
class TimeSetting extends IntId, Named, TemporalOn {
  var project: Project = uninitialized
  var campus: Option[Campus] = None
  var minutesPerUnit: Short = uninitialized
  var units: Buffer[CourseUnit] = new collection.mutable.ListBuffer[CourseUnit]

  def getUnit(indexno: Int): Option[CourseUnit] = {
    units.find(_.indexno == indexno)
  }

  def getUnitSpan(first: HourMinute, second: HourMinute): (Int, Int) = {
    var startUnit = 100
    var endUnit = 0
    val testUnit = new CourseUnit(first, second)
    for (unit <- units) {
      if (unit.overlapWith(testUnit)) {
        if (unit.indexno < startUnit) startUnit = unit.indexno
        if (unit.indexno > endUnit) endUnit = unit.indexno
      }
    }
    (startUnit, endUnit)
  }
}

/**
 * 课程小节
 */
class CourseUnit extends IntId, Named {
  var indexno: Int = uninitialized
  var beginAt: HourMinute = uninitialized
  var endAt: HourMinute = uninitialized
  var setting: TimeSetting = uninitialized
  var part: DayPart = uninitialized
  var enName: String = uninitialized

  def this(beginAt: HourMinute, endAt: HourMinute) = {
    this()
    this.beginAt = beginAt
    this.endAt = endAt
  }

  def overlapWith(that: CourseUnit): Boolean = that.endAt > this.beginAt && this.endAt > that.beginAt
}

/** 连续周连续,单周,双周,任意 */
enum CircleWeekTypes {
  case Continuely, Odd, Even, Random
}
