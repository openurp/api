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
package org.openurp.edu.program.plan.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.edu.base.model.{ Course, Terms }

/**
 * 抽象计划内课程
 * @author chaostone
 * @since 2009
 */
abstract class AbstractPlanCourse extends LongId with PlanCourse with Cloneable with Remark {
  /**
   * 课程组
   */
  var group: CourseGroup = _

  /**
   * 课程
   */
  var course: Course = _

  /**
   * 学期
   */
  var terms: Terms = _

  /**
   * 是否必修
   */
  var compulsory: Boolean = _

}
