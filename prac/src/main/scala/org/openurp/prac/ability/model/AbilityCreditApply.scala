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

package org.openurp.prac.ability.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.{AuditStatus, Department, Semester}
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.Certificate

import java.time.YearMonth

/** 学生能力证书学分申请
 */
class AbilityCreditApply extends LongId with Updated {
  /** 学生 */
  var std: Student = _
  /** 申请学期 */
  var semester: Semester = _
  /** 证书类型 */
  var certificate: Certificate = _
  /** 审核部门 */
  var auditDepart: Department = _
  /** 通过门数，-1表示全部通过 */
  var subjectCnt: Option[Int] = None
  /** 证书内课程 */
  var subjects: String = _
  /** 是否完成证书所有课程 */
  var finished: Boolean = _
  /** 证书编号 */
  var certificateNo: Option[String] = None
  /** 获得年月 */
  var acquiredOn: YearMonth = _
  /** 申请理由 */
  var reasons: Option[String] = None
  /** 审核意见 */
  var auditOpinion: Option[String] = None
  /** 成绩单附件路径 */
  var attachmentPath: String = _
  /** 申请状态 */
  var status: AuditStatus = AuditStatus.Draft
  /** 认定的学分数 */
  var credits: Option[Float] = None
}
