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

package org.openurp.base.edu.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.*
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.{Department, ProjectBased}

/** 课程群组
 */
class Curriculum extends LongId, ProjectBased, Updated, Coded, TemporalOn, Named, Remark {

  /** 课程英文名 */
  var enName: Option[String] = None

  /** 院系 */
  var department: Department = _

  /** 教研室 */
  var office: Option[TeachingOffice] = None

  /** 负责人 */
  var director: Option[Teacher] = None

  /** 教师 */
  var teachers = Collections.newSet[Teacher]

  /** 课程 */
  var courses = Collections.newBuffer[Course]

  /** 课程数量 */
  var courseCount: Int = _

  /** 教师数量 */
  var teacherCount: Int = _
}
