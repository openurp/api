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
import org.beangle.commons.lang.time.{CycleTimeDigest, WeekTime}
import org.beangle.data.model.pojo.{DateRange, Named}
import org.beangle.data.model.{Component, LongId}
import org.openurp.base.model.{Campus, Department, School, User}
import org.openurp.base.resource.model.Classroom
import org.openurp.code.edu.model.ActivityType

import java.time.temporal.ChronoUnit
import java.time.{Instant, LocalDate}
import scala.collection.mutable

class RoomApply extends LongId {

  /** 学校 */
  var school: School = _

  /** 借用人 */
  var applicant: Applicant = _

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

  /** 部门审核是否通过 */
  var departApproved: Option[Boolean] = None

  /** 是否通过 */
  var approved: Option[Boolean] = None

  /** 通过时间 */
  var approvedAt: Option[Instant] = None

  /** 分配教室 */
  var rooms: mutable.Set[Classroom] = Collections.newSet[Classroom]

}

class Activity extends Component, Named {
  /** 主讲人 */
  var speaker: String = _

  /** 活动类型 */
  var activityType: ActivityType = _

  /** 出席对象 */
  var attendance: Option[String] = None

  /** 出席人数 */
  var attendanceNum: Int = _
}

/** 借用人 */
class Applicant extends Cloneable, Component {
  /** 借用人 */
  var user: User = _

  /** 归口部门 */
  var auditDepart: Department = _

  /** 移动电话 */
  var mobile: String = _

  /** 电子邮件 */
  var email: Option[String] = None
}

/** 时间要求 */
class TimeRequest extends Component, DateRange {
  /** 借用时间要求 */
  var timeComment: Option[String] = None

  /** 申请时间 */
  var times: mutable.Buffer[WeekTime] = Collections.newBuffer[WeekTime]

  def toApplyTime(): ApplyTime = {
    val t = new ApplyTime()
    if (times.nonEmpty) {
      val dates = Collections.newSet[LocalDate]
      times foreach { t => dates.addAll(t.dates) }
      val dateList = dates.toList.sorted
      t.beginOn = dateList.head
      t.endOn = dateList.last
      val secondDay = if dateList.size == 1 then t.beginOn else dateList.tail.head
      t.cycle = Math.abs(ChronoUnit.DAYS.between(beginOn, secondDay).toInt)
      if (t.cycle == 0) t.cycle = 1
      t.beginAt = times.head.beginAt
      t.endAt = times.head.endAt
      t
    } else {
      null
    }
  }

  override def toString: String = CycleTimeDigest.digest(times, "<br>")
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
