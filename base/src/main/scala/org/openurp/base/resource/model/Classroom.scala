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

package org.openurp.base.resource.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.*
import org.openurp.base.model.*
import org.openurp.code.asset.model.ClassroomType

import scala.collection.mutable
import scala.compiletime.uninitialized

/**
 * 教室
 */
class Classroom extends LongId, Named, EnNamed, Coded, Updatable, TemporalOn {

  /** 学校 */
  var school: School = uninitialized

  /** 项目列表 */
  var projects: mutable.Set[Project] = Collections.newSet[Project]

  /** 房间号 */
  var roomNo: Option[String] = None

  /** 所属校区 */
  var campus: Campus = uninitialized

  /** 所属建筑 */
  var building: Option[Building] = None

  /** 简称 */
  var shortName: Option[String] = None

  /** 教室类型 */
  var roomType: ClassroomType = uninitialized

  /** 楼层 */
  var floorNo: Int = uninitialized

  /** 容量 */
  var capacity: Int = uninitialized

  /** 上课容量 */
  var courseCapacity: Int = uninitialized

  /** 考试容量 */
  var examCapacity: Int = uninitialized

  /** 使用部门 */
  var departs: mutable.Set[Department] = Collections.newSet[Department]

  /** 设备列表 */
  var devices: mutable.Set[Device] = Collections.newSet[Device]

  def activeDevices(semester: Semester): Iterable[Device] = {
    devices.filter(_.within(semester.beginOn))
  }

  def examDescription: String = {
    campus.name + " " + name + " " + roomType.name + "(" + examCapacity + ")"
  }

}
