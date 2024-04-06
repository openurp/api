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

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, TemporalOn, Updated}
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.{AuditStatus, Department, User}
import org.openurp.code.edu.model.CourseGeneralType

import java.time.Instant

/** 课程简介
 *
 */
class CourseProfile extends LongId, Updated, TemporalOn, Remark {

  /** 课程 */
  var course: Course = _

  /** 简介 */
  var description: String = _

  /** 英文简介 */
  var enDescription: Option[String] = None

  /** 先修课程 */
  var prerequisites: Option[String] = None

  /** 适用专业 */
  var majors: Option[String] = None

  /** 教材 */
  var textbooks: Option[String] = None

  /** 参考数目 */
  var bibliography: Option[String] = None

  /** 辅助资料 */
  var materials: Option[String] = None

  /** 课程网站地址 */
  var website: Option[String] = None

  /** 更新人 */
  var writer: Option[User] = None

  /** 课程分类 */
  var generalType: Option[CourseGeneralType] = None

  /** 状态 */
  var status: AuditStatus = AuditStatus.Draft

  /** 开课院系 */
  var department: Department = _

  /** 负责人 */
  var director: Option[Teacher] = None

  /** 审核人 */
  var auditor: Option[User] = None

  /** 审核时间 */
  var auditAt: Option[Instant] = None

  def published: Boolean = status == AuditStatus.Published

}
