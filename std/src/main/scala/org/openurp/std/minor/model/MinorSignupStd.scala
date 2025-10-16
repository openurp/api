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

package org.openurp.std.minor.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, Named, Updated}
import org.openurp.base.edu.model.MinorMajor
import org.openurp.code.edu.model.{DisciplineCategory, Institution}
import org.openurp.code.person.model.Gender

import java.time.LocalDate
import scala.collection.mutable

/** 报名信息
 */
class MinorSignupStd extends LongId, Coded, Named, Updated {
  /** 设置 */
  var setting: MinorSignupSetting = _
  /** 性别 */
  var gender: Gender = _
  /** 出生日期 */
  var birthday: LocalDate = _
  /** 身份证号 */
  var idcard: String = _
  /** 电话 */
  var mobile: String = _
  /** 院系 */
  var department: String = _
  /** 主修专业 */
  var major: String = _
  /** 班级 */
  var squad: Option[String] = None
  /** 地址 */
  var address: Option[String] = None
  /** 绩点 */
  var gpa: Option[Float] = None
  /** 学科门类 */
  var category: Option[DisciplineCategory] = None
  /** 学校 */
  var institution: Institution = _
  /** 报名志愿 */
  var majors: mutable.Buffer[MinorSignupStdMajor] = Collections.newBuffer[MinorSignupStdMajor]
  /** 是否社会学院 */
  var fromSocial: Boolean = false
  /** 所在单位 */
  var fromOrg: Option[String] = None
  /** 是否校内学生 */
  var inside: Boolean = _
  /** 照片路径 */
  var photoPath: Option[String] = None

  def firstMajor: MinorMajor = {
    majors.find(_.idx == 1).map(_.major).orNull
  }

  def secondMajor: Option[MinorMajor] = {
    majors.find(_.idx == 2).map(_.major)
  }

  def updateOption(idx: Int, o: MinorSignupOption): MinorSignupStdMajor = {
    majors.find(_.idx == idx) match {
      case Some(i) =>
        i.major = o.major
        i
      case None =>
        val n = new MinorSignupStdMajor(idx, this, o.major)
        majors += n
        n
    }
  }

  def removeOption(o: MinorSignupOption): Unit = {
    majors.find(_.major == o.major) foreach { i =>
      majors.subtractOne(i)
    }
  }

  def removeOption(idx: Int): Unit = {
    majors.find(_.idx == idx) foreach { i =>
      majors.subtractOne(i)
    }
  }
}
