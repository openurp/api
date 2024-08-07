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

package org.openurp.edu.course.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.{Semester, User}
import org.openurp.edu.clazz.model.Clazz

import java.time.Instant
import scala.collection.mutable

/** 教案
 */
@beta
class ClazzProgram extends LongId, Updated {

  /** 教学任务 */
  var clazz: Clazz = _

  /** 学期 */
  var semester: Semester = _

  /** 授课内容 */
  var designs: mutable.Buffer[LessonDesign] = Collections.newBuffer[LessonDesign]

  /** 作者 */
  var writer: User = _

  def this(clazz: Clazz) = {
    this()
    this.clazz = clazz
    this.semester = clazz.semester
    this.updatedAt = Instant.now
  }

  def get(idx: Int): Option[LessonDesign] = {
    designs.find(_.idx == idx)
  }
}
