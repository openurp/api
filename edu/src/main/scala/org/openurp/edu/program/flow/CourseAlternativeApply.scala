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

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.flow
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.edu.model.Course
import org.openurp.base.model.User
import org.openurp.base.std.model.Student

import java.time.Instant

/** 学生个人替代课程申请
 */
@flow
class CourseAlternativeApply extends LongId with Updated with Remark {

  /** 学生 */
  var std: Student = _

  /** 原课程 */
  var olds = Collections.newSet[Course]

  /** 新课程 */
  var news = Collections.newSet[Course]

  /** 是否审批通过 */
  var approved: Option[Boolean] = None

  /** 审核人 */
  var auditor: Option[User] = None

  /** 回复 */
  var reply: Option[String] = None

  /** 审核时间 */
  var auditAt: Option[Instant] = None

  def approve(passed: Boolean, auditor: User, reply: Option[String]): Unit = {
    this.approved = Some(passed)
    this.auditAt = Some(Instant.now)
    this.auditor = Some(auditor)
    this.reply = reply
  }

}
