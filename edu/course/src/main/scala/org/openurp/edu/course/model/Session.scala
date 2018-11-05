/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright (c) 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.course.model

import org.beangle.commons.lang.time.WeekTime
import org.beangle.data.model.LongId
import org.openurp.edu.base.Activity
import org.openurp.edu.base.model.{ Classroom, Teacher }
import java.time.LocalDate

/**
 * 教学活动
 * </p>
 * 上课对象是任务对应的教学班学生
 *
 * @has 1..* AssignedTo 1..* Teacher
 * @depend - - - Clazz
 */
class Session extends LongId with Ordered[Session] with Activity {

  /** 教学任务 */
  var clazz: Clazz = _

  /** 上课时间 */
  var time: WeekTime = _

  /** 授课教师列表 */
  var teachers: collection.mutable.Set[Teacher] = _

  /** 教室列表 */
  var rooms: collection.mutable.Set[Classroom] = _

  /** 排课备注 */
  var remark: Option[String] = None

  override def compare(other: Session): Int = {
    //fix me
    -1
  }

  override def startOn: LocalDate = {
    if (null != time) time.startOn else null
  }
}
