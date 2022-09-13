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

package org.openurp.edu.extern.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.edu.model.Course
import org.openurp.base.model.{AuditStatus, Department, Semester}
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{ExamStatus, GradingMode}
import org.openurp.edu.extern.code.CertificateSubject

import java.time.LocalDate
import scala.collection.mutable

/**
 * 校外证书成绩申请
 */
class CertExemptApply extends LongId with Updated {
  /** 学生 */
  var std: Student = _
  /** 申请学期 */
  var semester: Semester = _
  /** 成绩 */
  var scoreText: String = _
  /** 成绩记录方式 */
  var gradingMode: GradingMode = _
  /** 审核部门 */
  var auditDepart: Department = _
  /** 科目 */
  var subject: CertificateSubject = _
  /** 证书编号 */
  var certificate: Option[String] = None
  /** 获得日期 */
  var acquiredOn: LocalDate = _
  /** 免修课程 */
  var courses: mutable.Set[Course] = Collections.newSet[Course]
  /** 申请理由 */
  var reasons: String = _
  /** 审核意见 */
  var auditOpinion: Option[String] = None
  /** 成绩单附件路径 */
  var attachmentPath: String = _
  /** 申请状态 */
  var status: AuditStatus = AuditStatus.Draft
}
