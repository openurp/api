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
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{Certificate, ExamStatus, GradingMode}

import java.time.YearMonth
import scala.collection.mutable

/**
 * 校外证书成绩
 */
class CertificateGrade extends LongId, Updated {

  var std: Student = _

  /** 学年学期 */
  var semester: Semester = _

  /** 数字分数 */
  var score: Option[Float] = None

  /** 分数 */
  var scoreText: String = _

  /** 是否通过 */
  var passed: Boolean = _

  /** 证书名称 */
  var certificate: Certificate = _

  /** 证书内课程 */
  var subject: Option[String] = None

  /** 证书号 */
  var certificateNo: Option[String] = None

  /** 准考证号 */
  var examNo: Option[String] = None

  /** 获得年月 */
  var acquiredOn: YearMonth = _

  /** 成绩记录方式 */
  var gradingMode: GradingMode = _

  /** 考试状态 */
  var examStatus: ExamStatus = _

  /** 免修课程 */
  var exempts: mutable.Set[Course] = Collections.newSet[Course]

  /** 状态 */
  var status: Int = _
}
