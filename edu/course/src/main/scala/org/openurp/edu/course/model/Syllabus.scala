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
package org.openurp.edu.course.model

import java.time.Instant

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{DateRange, Updated}
import org.openurp.base.model.{Department, User}
import org.openurp.edu.base.model._

import scala.collection.mutable

/** 课程教学大纲
 *
 */
class Syllabus extends LongId with Updated with DateRange {

  /** 课程 */
  var course: Course = _

  /** 修订时的学年学期 */
  var semester: Semester = _

  /** 开课院系 */
  var department: Department = _

  /** 教研室 */
  var teachingGroup: Option[TeachingGroup] = None

  /** 附件 */
  var attachments: mutable.Buffer[SyllabusFile] = Collections.newBuffer[SyllabusFile]

  /** 作者 */
  var author: User = _

  /** 状态 */
  var status: SyllabusStatus.Status = SyllabusStatus.Draft

  /** 审核人 */
  var auditor: Option[User] = None

  /** 审核时间 */
  var auditAt: Option[Instant] = None
}
