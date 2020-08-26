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

import java.time.LocalDate

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.LongId
import org.openurp.edu.base.model.Classroom
import org.openurp.edu.base.model.Teacher

/***
 * 具体一次上课的内容
 */
class Lesson extends LongId {

  var clazz: Clazz = _

  var openOn: LocalDate = _

  var beginAt: HourMinute = _

  var endAt: HourMinute = _

  var contents: String = _

  /** 授课教师列表 */
  var teachers: collection.mutable.Set[Teacher] = Collections.newSet[Teacher]

  /** 教室列表 */
  var rooms: collection.mutable.Set[Classroom] = Collections.newSet[Classroom]
}
