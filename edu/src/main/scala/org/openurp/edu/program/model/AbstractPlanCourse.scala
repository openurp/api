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

import org.beangle.data.model.LongId
import org.openurp.base.edu.model.{Course, Terms}

/**
 * 抽象计划内课程
 *
 * @author chaostone
 * @since 2009
 */
abstract class AbstractPlanCourse extends LongId, PlanCourse, Cloneable {

  /** 课程组 */
  var group: CourseGroup = _

  /** 课程 */
  var course: Course = _

  /** 是否必修 */
  var compulsory: Boolean = _

  /** 开课学期 */
  var terms: Terms = _

  /** 序号 */
  var idx: Short = _

  def matchTerm(t: Terms): Boolean = {
    this.terms.matches(t)
  }
}
