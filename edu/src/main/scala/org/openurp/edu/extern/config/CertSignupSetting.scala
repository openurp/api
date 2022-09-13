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

package org.openurp.edu.extern.config

import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.openurp.edu.extern.code.CertificateSubject

import java.time.LocalDate
import scala.collection.mutable

/**
 * 资格考试报名科目设置
 *
 * @author chaostone
 */
@config
class CertSignupSetting extends LongId {
  /** 报名科目 */
  var subject: CertificateSubject = _
  /** 报名设置(期号) */
  var config: CertSignupConfig = _
  /** 要求报名费 */
  var feeOfSignup: Int = _
  /** 要求材料费 */
  var feeOfMaterial: Int = _
  /** 要求考纲费 */
  var feeOfOutline: Int = _
  /** 最大学生数(0或者null表示不限制) */
  var maxStd: Int = _
  /** 报名时要求通过的科目 */
  var dependsOn: Option[CertificateSubject] = None
  /** 有冲突的科目 */
  var exclusives: mutable.Set[CertificateSubject] = new mutable.HashSet[CertificateSubject]
  /** 通过后是否可以重考 */
  var reExamAllowed: Boolean = false
  /** 考试日期 */
  var examOn: Option[LocalDate] = None
  /** 考试开始时间 */
  var examBeginAt: HourMinute = HourMinute.Zero
  /** 考试结素和时间 */
  var examEndAt: HourMinute = HourMinute.Zero
  /** 报名条件 */
  var scopes: mutable.Buffer[CertSignupScope] = new mutable.ArrayBuffer[CertSignupScope]

  def this(subject: CertificateSubject, examOn: LocalDate, beginAt: HourMinute, endAt: HourMinute) = {
    this()
    this.subject = subject
    this.reExamAllowed = false
    this.examOn = Some(examOn)
    this.examBeginAt = beginAt
    this.examEndAt = endAt
  }

  def isTimeCollision(setting: CertSignupSetting): Boolean = {
    examOn match {
      case None => false
      case Some(e1) =>
        setting.examOn match {
          case None => false
          case Some(e2) => e1 == e2 && this.examBeginAt < setting.examEndAt && setting.examBeginAt < this.examEndAt
        }
    }
  }

}
