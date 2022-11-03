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

package org.openurp.std.info.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, Named}
import org.openurp.base.edu.model.{Direction, Major, Teacher}
import org.openurp.base.model.{Department, EduLevelBased}
import org.openurp.base.std.code.StdType
import org.openurp.base.std.model.{Grade, Squad}
import org.openurp.code.edu.model.{EducationDegree, EducationMode, EducationResult, StudyType}
import org.openurp.code.geo.model.Country
import org.openurp.code.person.model.*

import java.time.{LocalDate, YearMonth}

/**
 * 新生入学信息
 */
class Freshman extends LongId, Coded, Named, EduLevelBased {
  //----------基本信息---------------
  /** 性别 */
  var gender: Gender = _

  /** 身份证件类型 */
  var idType: IdType = _

  /** 证件号码 */
  var idCode: String = _

  /** 出生日期 */
  var birthday: Option[LocalDate] = None

  /** 出生地 */
  var birthplace: Option[String] = None

  /** 籍贯 */
  var homeTown: Option[String] = None

  /** 民族 */
  var nation: Option[Nation] = None

  /** 政治面貌 */
  var politicalStatus: Option[PoliticalStatus] = None

  /** 国籍/地区 */
  var country: Option[Country] = None

  /** 婚姻状况 */
  var maritalStatus: Option[MaritalStatus] = None

  /** 入团时间 */
  var joinCylOn: Option[YearMonth] = None

  /** 入党时间 */
  var joinCpcOn: Option[YearMonth] = None

  /** 工作单位或学校 */
  var organization: Option[String] = None

  //---------学籍信息----------
  /** 年级 */
  var grade: Grade = _

  /** 院系 */
  var department: Department = _

  /** 专业 */
  var major: Major = _

  /** 专业方向 */
  var direction: Option[Direction] = None

  /** 行政班级 */
  var squad: Option[Squad] = None

  /** 学生类别 */
  var stdType: StdType = _

  /** 入学日期 */
  var studyOn: LocalDate = _

  /** 学习年限 */
  var duration: Float = _

  /** 学习形式 全日制/业余/函授 */
  var studyType: StudyType = _

  /** 导师 */
  var tutor: Option[Teacher] = None

  /** 培养方式 */
  var educationMode: Option[EducationMode] = None

  /** 最后学历 */
  var lastEduDegree: Option[EducationDegree] = None

  //------联系信息--------------
  /** 电子邮箱 */
  var email: Option[String] = None

  /** 电话 */
  var phone: Option[String] = None

  /** 宿舍编号 */
  var dormitoryNo: Option[String] = None
}
