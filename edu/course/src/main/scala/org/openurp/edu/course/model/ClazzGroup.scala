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

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Named
import org.openurp.base.model.Department
import org.openurp.edu.base.ProjectBased
import org.openurp.edu.base.model.Course
import org.openurp.edu.base.model.Semester

/**
 * 教学任务课程组
 */
class ClazzGroup extends LongId with ProjectBased with Named {

  /** 学期 */
  var semester: Semester = _

  /**开课部门*/
  var teachDepart: Option[Department] = None

  /**课程*/
  var course: Option[Course] = None

  /** 任务集合 */
  var clazzes: collection.mutable.Set[Clazz] = _

}
