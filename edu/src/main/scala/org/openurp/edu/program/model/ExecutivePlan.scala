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

import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.Direction
import org.openurp.base.model.Department
import org.openurp.code.edu.model.CourseRank

/** 执行计划
 *
 * @author chaostone
 */
class ExecutivePlan extends AbstractCoursePlan, CoursePlan {

  /** 部门(培养方案的部门或者子部门) */
  var department: Department = _
}

/** 执行计划课程组
 *
 * @author chaostone
 */
class ExecutiveCourseGroup extends AbstractCourseGroup {

  /** 该组针对的专业方向 */
  var direction: Option[Direction] = None
}

/** 执行计划课程
 */
class ExecutivePlanCourse extends AbstractPlanCourse, Executable, Remark {

}
