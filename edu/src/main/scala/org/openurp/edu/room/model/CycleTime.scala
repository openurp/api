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

package org.openurp.edu.room.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.Strings
import org.beangle.commons.lang.time.{HourMinute, WeekDay, WeekTime}
import org.openurp.edu.room.model.CycleTime.CycleTimeType.*
import org.openurp.edu.room.model.CycleTime.{CycleTimeType, ToWeekTimeBuilder}

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object CycleTime {
  enum CycleTimeType(val id: Int) {
    case Day extends CycleTimeType(1)
    case Week extends CycleTimeType(2)
    case Month extends CycleTimeType(4)
  }

  def apply(beginOn: LocalDate, endOn: LocalDate, beginAt: HourMinute, endAt: HourMinute,
            cycleCount: Int = 1, cycleType: CycleTimeType = CycleTimeType.Day): CycleTime = {
    val cd = new CycleTime
    cd.beginOn = beginOn
    cd.endOn = endOn
    cd.beginAt = beginAt
    cd.endAt = endAt
    cd.cycleCount = cycleCount
    cd.cycleType = cycleType
    cd
  }

  class ToWeekTimeBuilder(beginAt: HourMinute, endAt: HourMinute) {

    private val times = Collections.newBuffer[WeekTime]

    def build(): List[WeekTime] = times.toList

    /** 在TimeUnitBuilder里添加一个日期
     *
     * @param start
     */
    def add(start: LocalDate): Unit = {
      val time = WeekTime.of(start, beginAt, endAt)
      if (times.isEmpty) times += time
      else {
        times.find(t => t.mergeable(time, 15)) match {
          case Some(t) => t.merge(time, 15)
          case None => times += time
        }
      }
    }

    /** 添加以start为起点，cycle为单位，count为步进，循环添加日期，直到end为止
     *
     * @param start
     * @param end
     * @param cycleType
     * @param count
     */
    def addRange(start: LocalDate, end: LocalDate, cycleType: CycleTimeType, count: Int = 1): Unit = {
      var startOn = start
      while (!startOn.isAfter(end)) {
        add(startOn)
        cycleType match {
          case Day => startOn = startOn.plusDays(count)
          case Week => startOn = startOn.plusWeeks(count)
          case Month => startOn = startOn.plusMonths(count)
        }
      }
    }
  }

}

object CycleTimeDigest {
  private val wMap = Map("Mon" -> "一", "Tue" -> "二", "Wed" -> "三", "Thu" -> "四", "Fri" -> "五", "Sat" -> "六", "Sun" -> "日")
  private val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
  private val format2 = DateTimeFormatter.ofPattern("MM-dd")

  def digest(times: collection.Seq[WeekTime], delimeter: String = ","): String = {
    if (times.isEmpty) return ""
    val timeList = Collections.newBuffer[String]
    val timeMap = new mutable.HashMap[(HourMinute, HourMinute), mutable.HashSet[LocalDate]]()
    times foreach { t =>
      val dates = timeMap.getOrElseUpdate((t.beginAt, t.endAt), new mutable.HashSet[LocalDate]())
      dates.addAll(t.dates)
    }
    val cycleTimes = new mutable.ArrayBuffer[CycleTime]
    timeMap foreach { case ((b, e), dates) =>
      if (dates.size == 1) {
        cycleTimes.addOne(CycleTime(dates.head, dates.head, b, e, 1, CycleTimeType.Day))
      } else {
        val dateList = new mutable.ArrayBuffer[LocalDate]
        dateList.addAll(dates).sortInPlace()

        val intervalMap = calcIntervals(dateList)
        if (intervalMap.size == 1) { //只有一种间隔
          val period = intervalMap.head._1
          if (period % 7 == 0) {
            cycleTimes.addOne(CycleTime(dateList.head, dateList.last, b, e, period / 7, CycleTimeType.Week))
          } else {
            cycleTimes.addOne(CycleTime(dateList.head, dateList.last, b, e, period, CycleTimeType.Day))
          }
        } else { //多种间隔
          dateList.groupBy(_.getDayOfWeek) foreach { case (dofw, wdates) =>
            val wIntervalMap = calcIntervals(wdates)
            if (wIntervalMap.size == 1) {
              val period = wIntervalMap.head._1
              cycleTimes.addOne(CycleTime(wdates.head, wdates.last, b, e, period / 7, CycleTimeType.Week))
            } else {
              wdates foreach { d => cycleTimes.addOne(CycleTime(d, d, b, e)) }
            }
          }
        }
      }
    }

    cycleTimes.sortBy(x => x.beginOn.toString + "" + x.beginAt.toString).foreach(cd => {
      val sb = new StringBuilder
      if (cd.isOneDay) sb.append(cd.endOn.format(format))
      else {
        sb.append(cd.beginOn.format(format))
        sb.append("~")
        if (cd.beginOn.getYear == cd.endOn.getYear) sb.append("").append(cd.endOn.format(format2))
        else sb.append("").append(cd.endOn.format(format))
        if (cd.cycleType == CycleTimeType.Week) {
          if (cd.cycleCount.intValue != 1) sb.append(" 每" + cd.cycleCount + "周周") else sb.append(" 每周")

          val wd = WeekDay.of(cd.beginOn)
          sb.append(wMap(wd.toString))
        } else {
          if (cd.cycleCount.intValue == 1) {
            if (cd.beginOn != cd.endOn) sb.append(" 每天")
          } else {
            sb.append(" 每" + cd.cycleCount + "天")
          }
        }
      }
      sb.append(" ")
      sb.append(cd.beginAt)
      sb.append("~")
      sb.append(cd.endAt)
      timeList.+=(sb.toString)
    })
    Strings.join(timeList, delimeter)
  }

  private def calcIntervals(dates: collection.Seq[LocalDate]): Map[Int, Int] = {
    val intervalMap = new mutable.HashMap[Int, Int]()
    var head: LocalDate = null
    dates.foreach { d =>
      if (null != head) {
        val i = Math.abs(ChronoUnit.DAYS.between(d, head).intValue)
        intervalMap.put(i, intervalMap.getOrElseUpdate(i, 0) + 1)
      }
      head = d
    }
    intervalMap.toMap
  }

}

class CycleTime extends Cloneable with Serializable {
  /** 开始日期 */
  var beginOn: LocalDate = _
  /** 结束日期 */
  var endOn: LocalDate = _
  /** 开始时间 */
  var beginAt: HourMinute = _
  /** 结束时间 */
  var endAt: HourMinute = _
  /** 单位 */
  var cycleType: CycleTimeType = _
  /** 单位数量 */
  var cycleCount: Int = _

  def isOneDay: Boolean = {
    this.beginOn == this.endOn
  }

  def getCycleDays: Int = {
    cycleType match
      case Day => cycleCount
      case Month => cycleCount * 30
      case Week => cycleCount * 7
  }

  def convert(): List[WeekTime] = {
    val builder = new ToWeekTimeBuilder(beginAt, endAt)
    builder.addRange(beginOn, endOn, cycleType, cycleCount)
    builder.build()
  }

}
