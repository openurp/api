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

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.{Coded, InstantRange, Named}
import org.openurp.base.model.{Project, Semester}
import org.openurp.edu.extern.code.{CertificateCategory, CertificateSubject}

import java.time.Instant
import scala.collection.mutable

/**
 * 资格考试报名设置（期号）
 *
 * @author chaostone
 */
@config
class CertSignupConfig extends LongId with Coded with Named with InstantRange {
  /** 考试类型 */
  var category: CertificateCategory = _
  /** 教学项目 */
  var project: Project = _
  /** 报名科目设置 */
  var settings: mutable.Buffer[CertSignupSetting] = new mutable.ArrayBuffer[CertSignupSetting]
  /** 在规定的时间段内,是否可以开放 */
  var opened = false
  /** 通知 */
  var notice: String = _
  /** 学年学期 */
  var semester: Semester = _
  /** 是否预报名 */
  var prediction: Boolean = _
  /** 允许报名的最大门数 */
  var maxSubject: Int = _

  def isTimeSuitable: Boolean = {
    within(Instant.now)
  }

  def getSetting(subject: CertificateSubject): Option[CertSignupSetting] = {
    settings.find(_.subject == subject)
  }

  def addSetting(setting: CertSignupSetting): Unit = {
    settings += setting
    setting.config = this
  }

  def subjects: List[CertificateSubject] = {
    settings.map(_.subject).toList
  }
}
