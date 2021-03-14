/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
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
package org.openurp.edu.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.time.WeekTime
import org.beangle.data.model.LongId
import org.openurp.base.edu.Activity
import org.openurp.base.edu.model.{Classroom, Teacher}
import org.openurp.code.edu.model.{TeachingMethod, TeachingNature}

import java.time.LocalDate

/**
 * 教学活动
 * </p>
 * 上课对象是任务对应的教学班学生
 *
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

  /** 授课场所 */
  var places: Option[String] = None

  /** 授课性质 */
  var teachingNature: TeachingNature = _

  /** 授课方式 */
  var teachingMethod: TeachingMethod = _

  /** 针对授课小班 */
  var subclazzes = Collections.newBuffer[Subclazz]

  /** 对比活动 */
  override def compare(other: Session): Int = {
    //fix me
    -1
  }

  override def startOn: LocalDate = {
    if (null != time) time.startOn else null
  }
}
