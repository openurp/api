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

package org.openurp.edu.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.{Semester, User}

import java.time.Instant
import java.util.Locale
import scala.collection.mutable

/** 授课计划
 * 每个任务唯一
 */
class TeachingPlan extends LongId with Updated {

  /** 教学任务 */
  var clazz: Clazz = _

  /** 授课计划语言 */
  var docLocale: Locale = _

  /** 学期 */
  var semester: Semester = _

  /** 作者 */
  var author: Option[User] = None

  /** 授课内容 */
  var lessons: mutable.Buffer[Lesson] = Collections.newBuffer[Lesson]

  /** 文件大小 */
  var fileSize: Int = _

  /** 文件类型 */
  var mimeType: Option[String] = None

  /** 文件路径 */
  var filePath: Option[String] = None

  /** 是否通过 */
  var passed: Option[Boolean] = None

  /** 审核人 */
  var auditor: Option[User] = None

  /** 审核时间 */
  var auditAt: Option[Instant] = None

}
