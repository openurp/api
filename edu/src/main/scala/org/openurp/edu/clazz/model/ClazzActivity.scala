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

package org.openurp.edu.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.time.{WeekState, WeekTime}
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.Activity
import org.openurp.base.hr.model.Teacher
import org.openurp.base.resource.model.Classroom
import org.openurp.code.edu.model.TeachingNature

import java.time.LocalDate

/** 教学活动
 * 上课对象是任务对应的教学班学生
 */
class ClazzActivity extends LongId, Ordered[ClazzActivity], Activity, Cloneable, Remark {

  /** 教学任务 */
  var clazz: Clazz = _

  /** 上课时间 */
  var time: WeekTime = _

  /** 开始节次 */
  var beginUnit: Short = _

  /** 结束节次 */
  var endUnit: Short = _

  /** 授课教师列表 */
  var teachers: collection.mutable.Set[Teacher] = _

  /** 教室列表 */
  var rooms: collection.mutable.Set[Classroom] = _

  /** 授课性质 */
  var nature: TeachingNature = _

  /** 针对授课小班 */
  var subclazz: Option[Subclazz] = None

  /** 对比活动 */
  override def compare(that: ClazzActivity): Int = {
    // compare teacher// compare teacher
    var rs = teachers.size - that.teachers.size
    // compare room// compare room
    if (rs == 0) rs = rooms.size - that.rooms.size
    // compare weeks// compare weeks
    if (rs == 0) rs = time.weekstate.compareTo(that.time.weekstate)
    if (rs == 0) rs = time.startOn.compareTo(that.time.startOn)
    if (rs == 0) rs = time.beginAt.value - that.time.beginAt.value
    -1
  }

  override def startOn: LocalDate = {
    if (null != time) time.firstDay else null
  }

  override def clone(): ClazzActivity = {
    val obj = super.clone().asInstanceOf[ClazzActivity]
    obj.time = new WeekTime(this.time)
    obj.time.weekstate = new WeekState(this.time.weekstate.value)
    obj.rooms = Collections.newSet[Classroom]
    obj.teachers = Collections.newSet[Teacher]
    obj.rooms.addAll(this.rooms)
    obj.teachers.addAll(this.teachers)
    obj
  }

  /** 合并两个教学活动的时间
   *
   * @param that
   */
  def mergeTime(that: ClazzActivity): Unit = {
    if (this.time.beginAt >= that.time.beginAt) this.time.beginAt = that.time.beginAt
    if (this.time.endAt <= that.time.endAt) this.time.endAt = that.time.endAt
    this.beginUnit = math.min(this.beginUnit, that.beginUnit).toShort
    this.endUnit = math.max(this.endUnit, that.endUnit).toShort
    this.time.weekstate = new WeekState(this.time.weekstate.value | that.time.weekstate.value)
  }
}
