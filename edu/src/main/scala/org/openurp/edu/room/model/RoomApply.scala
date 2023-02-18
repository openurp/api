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
import org.beangle.commons.lang.time.{WeekDay, WeekTime}
import org.beangle.data.model.pojo.{DateRange, Named}
import org.beangle.data.model.{Component, LongId}
import org.openurp.base.edu.model.Classroom
import org.openurp.base.model.{Campus, Department, School, User}
import org.openurp.code.edu.model.ActivityType

import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import scala.collection.mutable

class RoomApply extends LongId {

  /** 学校 */
  var school: School = _

  /** 借用人 */
  var borrower: Borrower = _

  /** 时间要求 */
  var time: TimeRequest = _

  /** 教室要求 */
  var space: SpaceRequest = _

  /** 活动 */
  var activity: Activity = _

  /** 申请时间 */
  var applyAt: Instant = _

  /** 操作人 */
  var applyBy: User = _

  /** 部门审核 */
  var departCheck: Option[RoomApplyDepartCheck] = None

  /** 最终审核 */
  var finalCheck: Option[RoomApplyFinalCheck] = None

  /** 是否通过 */
  var approved: Option[Boolean] = None

  /** 分配教室 */
  var rooms: mutable.Set[Classroom] = Collections.newSet[Classroom]
}

class Activity extends Component with Named {
  /** 活动类型 */
  var activityType: ActivityType = _

  /** 主讲人：姓名及背景资料 */
  var speaker: String = _

  /** 出席对象 */
  var attendance: String = _

  /** 出席人数 */
  var attendanceNum: Int = _
}

/** 借用人 */
class Borrower extends Cloneable with Component {

  /** 归口部门 */
  var department: Department = _

  /** 借用人 */
  var applicant: String = _

  /** 移动电话 */
  var mobile: String = _

  /** 电子邮件 */
  var email: Option[String] = None
}

/** 时间要求 */
class TimeRequest extends Component with DateRange {
  /** 分钟 */
  var minutes: Int = 0

  /** 借用时间要求 */
  var timeComment: Option[String] = None

  /** 申请时间 */
  var times: mutable.Buffer[WeekTime] = Collections.newBuffer[WeekTime]

  def calcMinutes(): Unit = {
    var mins = 0
    times.foreach(time => {
      val daymins = time.endAt.interval(time.beginAt)
      val count = time.weekstate.size
      mins = mins + daymins * count
    })
    this.minutes = mins
  }

  override def toString: String = {
    val timeList = Collections.newBuffer[String]
    times.sortBy(_.startOn).sortBy(_.beginAt)
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val format2 = DateTimeFormatter.ofPattern("MM-dd")
    val dates = Collections.newBuffer[CycleTime]
    if (times.nonEmpty) {
      times.foreach{time =>  //循环节次信息
        time.dates.foreach { d => //遍历对应周状态通过的日期集合（每周的同一时间）
          var cd: CycleTime = null
          val datesIter= dates.iterator
          while(datesIter.hasNext && null==cd) { //遍历之前已经添加好的时间
            val cd1 = datesIter.next()
            val days = cd1.endOn.until(d, ChronoUnit.DAYS).toInt
            val minus_time = (time.endAt.minute - cd1.endAt.minute)
            if (days % 7 == 0 && minus_time == 0) { //如果整周的话
              if (cd1.isOneDay) { //cd1的开始日期等于结束日期
                cd1.endOn = d
                cd = cd1
              }else if (days == cd1.getCycleDays) { //或者等于7
                cd1.endOn = d
                cd = cd1
              }
            }
          }
          if (cd == null) {
            dates += CycleTime(d,time.beginAt,time.endAt)
          }
        }
      }
      dates.foreach(cd => {
        val sb = new StringBuilder
        if (cd.isOneDay) sb.append(cd.endOn.format(format))
        else {
          sb.append(cd.beginOn.format(format))
          sb.append("~")
          if (cd.beginOn.getYear == cd.endOn.getYear) sb.append("").append(cd.endOn.format(format2))
          else sb.append("").append(cd.endOn.format(format))
          if (cd.cycleCount.intValue != 1) sb.append(" 每" + cd.cycleCount + "周周")
          else sb.append(" 每周")

          val wMap = Map("Mon" -> "一", "Tue" -> "二", "Wed" -> "三", "Thu" -> "四", "Fri" -> "五", "Sat" -> "六", "Sun" -> "日")
          val wd = WeekDay.of(cd.beginOn)
          sb.append(Strings.replace(wMap.get(wd.toString()).get, "星期", ""))
        }
        sb.append(" ")
        sb.append(cd.beginAt)
        sb.append("~")
        sb.append(cd.endAt)
        timeList.+=(sb.toString)
      })
      Strings.join(timeList, "<br>")
    }
    else ""
  }
}

/** 教室要求 */
class SpaceRequest extends Component {
  /** 借用校区 */
  var campus: Campus = _

  /** 每个教室单元需要的座位数 */
  var unitAttendance: Int = 0

  /** 是否使用多媒体设备 */
  var requireMultimedia: Boolean = false

  /** 借用场所要求 */
  var roomComment: Option[String] = None

}

class RoomApplyDepartCheck extends LongId {
  /**教室申请*/
  var roomApply: RoomApply = _
  /** 是否审核通过 */
  var approved: Boolean = _
  /** 审核人 */
  var checkedBy: User = _
  /** 审核时间 */
  var checkedAt: Instant = _
  /** 具体意见 */
  var opinions: Option[String] = None
}

class RoomApplyFinalCheck extends LongId {
  /**教室申请*/
  var roomApply: RoomApply = _

  var approved: Boolean = _
  var checkedBy: User = _
  var checkedAt: Instant = _
  var opinions: Option[String] = None
}
