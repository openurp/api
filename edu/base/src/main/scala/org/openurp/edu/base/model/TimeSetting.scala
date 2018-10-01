/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.model

import scala.collection.mutable.Buffer

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.IntId
import org.beangle.data.model.pojo.Named
import org.beangle.data.model.pojo.TemporalOn
import org.openurp.base.model.Campus
import org.openurp.edu.base.code.model.DayPart

/**
 * 时间设置
 */
class TimeSetting extends IntId with Named with TemporalOn {
  var project: Project = _
  var campus: Option[Campus] = None
  var units: Buffer[CourseUnit] = new collection.mutable.ListBuffer[CourseUnit]
}

/**
 * 课程小节
 */
class CourseUnit extends IntId with Named {
  var indexno: Int = _
  var beginAt: HourMinute = _
  var endAt: HourMinute = _
  var setting: TimeSetting = _
  var part: DayPart = _
  var enName: String = _
}

/** 连续周连续,单周,双周,任意*/
object CircleWeekTypes extends Enumeration(1) {
  val Continuely, Odd, Even, Random = Value
}
