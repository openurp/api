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
import org.beangle.data.model.pojo.{TemporalOn, Updatable}
import org.openurp.base.edu.model.Course
import org.openurp.base.model.{AuditStatus, Department, Semester, User}

import java.time.Instant
import java.util.Locale
import scala.compiletime.uninitialized

/**
 * 课程教学大纲文档
 */
class SyllabusDoc extends LongId, Updatable, TemporalOn {
  /** 课程 */
  var course: Course = uninitialized

  /** 修订时的学年学期 */
  var semester: Semester = uninitialized

  /** 开课院系 */
  var department: Department = uninitialized

  /** 文件语言 */
  var docLocale: Locale = uninitialized

  /** 文件大小 */
  var docSize: Int = uninitialized

  /** 存储路径 */
  var docPath: String = uninitialized

  /** 作者 */
  var writer: User = uninitialized

  /** 状态 */
  var status: AuditStatus = AuditStatus.Draft

  /** 审核人 */
  var auditor: Option[User] = None

  /** 审核时间 */
  var auditAt: Option[Instant] = None
}
