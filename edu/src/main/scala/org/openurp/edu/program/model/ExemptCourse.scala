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

package org.openurp.edu.program.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.edu.model.Course
import org.openurp.base.model.{EduLevelBased, Project}
import org.openurp.base.std.model.Grade
import org.openurp.code.std.model.StdType

import scala.collection.mutable

/** 免修课程
 * 规定课程在哪些学生范围中，可以免修
 */
@beta
class ExemptCourse extends LongId, EduLevelBased, Updated, Remark {
  /** 起始年级 */
  var fromGrade: Grade = _
  /** 截止年级 */
  var toGrade: Option[Grade] = None
  /** 免修课程 */
  var course: Course = _
  /** 针对学生类别 */
  var stdTypes: mutable.Set[StdType] = Collections.newSet[StdType]
}
