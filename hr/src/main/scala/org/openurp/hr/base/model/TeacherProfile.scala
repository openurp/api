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

package org.openurp.hr.base.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.edu.code.TeacherType
import org.openurp.base.edu.model.Teacher
import org.openurp.base.model.Department
import org.openurp.code.edu.model.{Degree, DegreeLevel, EducationDegree}
import org.openurp.code.hr.model.WorkStatus
import org.openurp.code.job.model.ProfessionalTitle
import org.openurp.code.person.model.{Gender, IdType, Nation, PoliticalStatus}

import java.time.LocalDate

/**
 * 教师基本情况
 */
class TeacherProfile extends LongId with Updated {
  /** 教师 */
  var teacher: Teacher = _
  //基本信息
  /** 性别 */
  var gender: Gender = _

  /** 身份证件类型 */
  var idType: Option[IdType] = None

  /** 证件号码 */
  var idNumber: Option[String] = None

  /** 出生日期 */
  var birthday: Option[LocalDate] = None

  /** 民族 */
  var nation: Option[Nation] = None

  /** 政治面貌 */
  var politicalStatus: Option[PoliticalStatus] = None

  //任职情况
  /** 所在教学部门 */
  var department: Department = _

  /** 在职状态 */
  var status: WorkStatus = _

  /** 教师类型 */
  var teacherType: TeacherType = _

  /** 是否在编 */
  var formalHr: Boolean = _

  /** 是否有教师资格证 */
  var hasTqc: Boolean = _

  /** 教师资格证编号 */
  var tqcNumber: Option[String] = None

  /** 其他职业资格证书和等级说明 */
  var oqc: Option[String] = None

  //职称、学历和学位
  /** 最高职称 */
  var title: Option[ProfessionalTitle] = None

  /** 最高学历 */
  var educationDegree: Option[EducationDegree] = None

  /** 学位层次 */
  var degreeLevel: DegreeLevel = _

  /** 最高学位 */
  var degree: Option[Degree] = None

  /** 最高学位授予单位 */
  var degreeAwardBy: Option[String] = None

  //教学经历和科研成果
  /** 教学经历 */
  var teachingCareer: Option[String] = None

  /** 科研成果 */
  var harvest: Option[String] = None

  /** 方向 */
  var research: Option[String] = None

  /** 学术兼职 */
  var titles: Option[String] = None

  // 个人介绍和联系方式
  /** 个人简介 */
  var intro: String = _

  /** 联系方式 */
  var contact: Option[String] = None

  /** 全职工作单位 */
  var organization: Option[String] = None

  /** 个人主页 */
  var homepage: Option[String] = None
}
