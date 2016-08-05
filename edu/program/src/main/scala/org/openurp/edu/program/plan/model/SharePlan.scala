/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.program.plan.model

import org.beangle.data.model.Named
import org.beangle.data.model.LongId
import org.openurp.code.edu.model.Language
import org.openurp.edu.base.EducationBased
import org.openurp.edu.base.code.model.CourseAbilityRate

/**
 * 公共共享计划
 *
 * @author chaostone
 */
class SharePlan extends LongId with AbstractCoursePlan with EducationBased with Named with Cloneable {

  /**起始年级*/
  var fromGrade: String = _

  /**截止年级(包含)*/
  var toGrade: String = _

}

/**
 * 公共共享课程组(默认实现)
 */
class ShareCourseGroup extends LongId with AbstractCourseGroup {

  /**
   * 对应外语语种
   */
  var language: Option[Language] = None

  /**
   * *
   * 要求语言等级
   */
  var courseAbilityRate: Option[CourseAbilityRate] = None
}

/**
 * 公共共享课程组课程
 *
 * @author chaostone
 */
class SharePlanCourse extends AbstractPlanCourse with ExecutePlanCourse
