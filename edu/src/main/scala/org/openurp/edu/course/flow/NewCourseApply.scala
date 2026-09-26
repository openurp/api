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

package org.openurp.edu.course.flow

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.flow
import org.beangle.data.model.pojo.{EnNamed, Named, TemporalOn, Updatable}
import org.openurp.base.model.{AuditStatus, Department, ProjectBased, User}
import org.openurp.code.edu.model.*
import scala.compiletime.uninitialized

/** 新开课程申请
 */
@flow
class NewCourseApply extends LongId, ProjectBased, Updatable, TemporalOn, Named, EnNamed {
  var code: Option[String] = None
  /** 院系 */
  var department: Department = uninitialized
  /** 学分 */
  var defaultCredits: Float = uninitialized

  /** 课程模块 */
  var module: Option[CourseModule] = None
  /** 必修/选修/限选 */
  var rank: Option[CourseRank] = None
  /** 课程分类 */
  var category: NewCourseCategory = uninitialized
  /** 课程性质 (理论、实践、术科、实验) */
  var nature: CourseNature = uninitialized

  /** 学时/总课时 */
  var creditHours: Int = uninitialized
  /** 分类课时 */
  var hours = Collections.newBuffer[NewCourseApplyHour]
  /** 周课时 */
  var weekHours: Int = uninitialized

  /** 考试方式 */
  var examMode: ExamMode = uninitialized
  /** 成绩记录方式 */
  var gradingMode: GradingMode = uninitialized

  /** 课程标签 */
  var tags = Collections.newSet[CourseTag]
  /** 状态 */
  var status: AuditStatus = uninitialized
  /** 申请人 */
  var applicant: User = uninitialized
  /** 审核意见 */
  var opinions: Option[String] = None
}

@flow
class NewCourseApplyHour extends LongId {
  var courseApply: NewCourseApply = uninitialized
  var creditHours: Int = uninitialized
  var weeks: Int = uninitialized
  var nature: TeachingNature = uninitialized
}
