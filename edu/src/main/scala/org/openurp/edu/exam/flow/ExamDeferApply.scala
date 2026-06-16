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

package org.openurp.edu.exam.flow

import org.beangle.commons.json.{Json, JsonArray, JsonObject}
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.flow
import org.beangle.data.model.pojo.{Creatable, Remark, Updatable}
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{ExamDeferReason, ExamType}
import org.openurp.edu.clazz.model.Clazz

import java.time.Instant

/** 考试缓考申请
 */
@flow
class ExamDeferApply extends LongId, Creatable, Updatable, Remark {

  /** 教学任务 */
  var clazz: Clazz = _

  /** 学生 */
  var std: Student = _

  /** 考试类型 */
  var examType: ExamType = _

  /** 考试开始时间 */
  var examBeginAt: Option[Instant] = None

  /** 移动电话 */
  var mobile: Option[String] = None

  /** 申请原因 */
  var reason: ExamDeferReason = _

  /** 教师是否审核 */
  var teacherReviewed: Option[Boolean] = None

  /** 院系是否通过 */
  var collegeApproved: Option[Boolean] = None

  /** 是否通过 */
  var passed: Option[Boolean] = None

  /** 个人签名url */
  var stdSignUrl: Option[String] = None

  /** 状态 */
  var status: String = _

  /** 附件 */
  var attachments: JsonArray = Json.emptyArray

  /** 意见 */
  var opinions: JsonObject = Json.emptyObject
}
