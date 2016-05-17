/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.base.model

import scala.collection.mutable.Buffer

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.{ IntId, Named, NumId, TemporalOn }

/**
 * 假日安排
 */
class Holiday extends IntId with Named with TemporalOn{
  var school: School =_
}

/**
 * 时间设置
 */
class TimeSetting extends IntId with Named {
  var school:School = _
  var units: Buffer[CourseUnit] = new collection.mutable.ListBuffer[CourseUnit]
}

class CourseUnit extends NumId[java.lang.Short] with Named {
  var indexno: Int = _
  var beginAt: HourMinute = _
  var endAt: HourMinute = _
  var setting: TimeSetting = _
  var enName: String = _
}

object CircleTime {
  /**
   * weekState保留几位
   */
  val Reserved: Int = 2

  /** 连续周连续,单周,双周,任意*/
  object CircleWeekTypes extends Enumeration(1) {
    val Continuely, Odd, Even, Random = Value
  }
}
