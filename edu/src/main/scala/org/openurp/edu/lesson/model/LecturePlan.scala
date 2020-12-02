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
package org.openurp.edu.lesson.model

import java.time.Instant
import java.util.Locale

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.User
import org.openurp.edu.base.model.Semester
import org.openurp.edu.clazz.model.Clazz

/** 授课计划
 * 每个任务唯一
 */
class LecturePlan extends LongId with Updated {

  var clazz: Clazz = _

  var docLocale: Locale = _

  var semester: Semester = _

  var author: User = _

  var fileSize: Int = _

  var mimeType: String = _

  var filePath: String = _

  var passed: Option[Boolean] = None

  /** 审核人 */
  var auditor: Option[User] = None

  /** 审核时间 */
  var auditAt: Option[Instant] = None
}
