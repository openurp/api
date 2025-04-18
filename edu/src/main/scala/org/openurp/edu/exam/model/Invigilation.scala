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

package org.openurp.edu.exam.model;

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.{Department, User}

import java.time.Instant

/**
 * 监考任务
 */
class Invigilation extends LongId, Updated {

  /** 考场 */
  var examRoom: ExamRoom = _

  /** 监考院系 */
  var department: Department = _

  /** 监考人 */
  var invigilator: Option[User] = None

  /** 自定义监考 */
  var invigilatorName: Option[String] = None

  /** 是否是第一监考 */
  var chief: Boolean = _

  /** 是否发布 */
  var published: Boolean = _

  def this(examRoom: ExamRoom, department: Department, teacher: User) = {
    this()
    this.examRoom = examRoom
    this.department = department;
    this.invigilator = Some(teacher)
    this.updatedAt = Instant.now
  }

  def this(examRoom: ExamRoom, department: Department, teacherName: String) = {
    this()
    this.examRoom = examRoom;
    this.department = department;
    this.invigilatorName = Some(teacherName)
    this.updatedAt = Instant.now
  }

  def isSameMonitor(other: Invigilation): Boolean = {
    department == other.department && invigilator == other.invigilator
  }
}
