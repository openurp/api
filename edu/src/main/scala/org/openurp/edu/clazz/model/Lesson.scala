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

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.LongId
import org.openurp.base.edu.model.{Classroom, Teacher}
import org.openurp.code.edu.model.{TeachingMethod, TeachingNature}

import java.time.LocalDate

/**
 * 授课内容
 */
class Lesson extends LongId {

  /** 授课计划 */
  var plan: TeachingPlan = _

  /** 针对授课小班 */
  var subclazz: Option[Subclazz] = None

  /** 序号 */
  var idx: Int = _

  /** 内容 */
  var contents: String = _

  /** 开课日期 */
  var openOn: LocalDate = _

  /** 开课节次 */
  var units: String = _

  /** 开始时间 */
  var beginAt: HourMinute = _

  /** 结束时间 */
  var endAt: HourMinute = _

  /** 授课性质 */
  var teachingNature: TeachingNature = _

  /** 授课方式 */
  var teachingMethod: TeachingMethod = _

  /** 授课教师列表 */
  var teachers: collection.mutable.Set[Teacher] = _

  /** 教室列表 */
  var rooms: collection.mutable.Set[Classroom] = _

  /** 授课场所 */
  var places: Option[String] = None

}
