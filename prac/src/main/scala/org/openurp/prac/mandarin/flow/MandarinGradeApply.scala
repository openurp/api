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

package org.openurp.prac.mandarin.flow

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updatable
import org.openurp.base.model.{AuditStatus, Semester}
import org.openurp.base.std.model.Student

import java.time.YearMonth
import scala.compiletime.uninitialized

class MandarinGradeApply extends LongId, Updatable {

  def this(std: Student) = {
    this()
    this.std = std
  }

  /** 学生 */
  var std: Student = uninitialized
  /** 学年学期 */
  var semester: Semester = uninitialized
  /** 分数 */
  var score: Float = uninitialized
  /** 证书名称 */
  var certificateName: Option[String] = None
  /** 证书编号 */
  var certificateNo: String = uninitialized
  /** 获得年月 */
  var acquiredIn: YearMonth = uninitialized
  /** 审核意见 */
  var auditOpinion: Option[String] = None
  /** 成绩单附件路径 */
  var attachmentPath: String = uninitialized
  /** 申请状态 */
  var status: AuditStatus = AuditStatus.Draft
  /** 申请理由 */
  var reasons: Option[String] = None
}
