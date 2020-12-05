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
package org.openurp.edu.program.model

import org.openurp.base.model.{Campus, Department}
import org.openurp.base.edu.code.model.StdType
import org.openurp.base.edu.model.{Direction, Terms}

/** 执行计划
 * @author chaostone
 */
class ExecutionPlan extends AbstractCoursePlan with CoursePlan {

  /** 部门(培养方案的部门或者子部门) */
  var department: Department = _

  /** 学生类别 */
  var stdType: Option[StdType] = None

  /** 校区 */
  var campus: Option[Campus] = None

}

/** 执行计划课程组
 *
 * @author chaostone
 */
class ExecutionCourseGroup extends AbstractCourseGroup {

  /** 该组针对的专业方向 */
  var direction: Option[Direction] = None

}

/** 执行计划课程
 */
class ExecutionPlanCourse extends AbstractPlanCourse with Executable {

  /** 建议修读学期 */
  var suggestTerms: Terms = Terms.empty

}
