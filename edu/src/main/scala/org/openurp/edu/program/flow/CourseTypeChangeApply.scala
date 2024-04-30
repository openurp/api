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

package org.openurp.edu.program.flow

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.flow
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.edu.model.Course
import org.openurp.base.model.User
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.CourseType

import java.time.Instant

/** 课程类别转换申请
 */
@flow
class CourseTypeChangeApply extends LongId with Updated with Remark {

  /** 学生 */
  var std: Student = _

  var course: Course = _

  /** 原类型 */
  var oldType: CourseType = _

  /** 新类型 */
  var newType: CourseType = _

  /** 是否审批通过 */
  var approved: Option[Boolean] = None

  /** 审核人 */
  var auditor: Option[User] = None

  /** 回复 */
  var reply: Option[String] = None

  /** 审核时间 */
  var auditAt: Option[Instant] = None
}
