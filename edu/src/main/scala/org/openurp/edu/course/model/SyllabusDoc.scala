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

package org.openurp.edu.course.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{TemporalOn, Updated}
import org.openurp.base.edu.model.{Course, TeachingOffice}
import org.openurp.base.model.{AuditStatus, Department, Semester, User}

import java.time.Instant
import java.util.Locale

/**
 * 课程教学大纲文档
 */
class SyllabusDoc extends LongId with Updated with TemporalOn {
  /** 课程 */
  var course: Course = _

  /** 修订时的学年学期 */
  var semester: Semester = _

  /** 开课院系 */
  var department: Department = _

  /** 文件语言 */
  var docLocale: Locale = _

  /** 文件大小 */
  var docSize: Int = _

  /** 存储路径 */
  var docPath: String = _

  /** 作者 */
  var writer: User = _

  /** 状态 */
  var status: AuditStatus = AuditStatus.Draft

  /** 审核人 */
  var auditor: Option[User] = None

  /** 审核时间 */
  var auditAt: Option[Instant] = None
}
