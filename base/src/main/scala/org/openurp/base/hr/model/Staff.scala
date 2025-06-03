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

package org.openurp.base.hr.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, EnNamed, Named, TemporalOn, Updated}
import org.openurp.base.model.{Department, School}
import org.openurp.code.edu.model.{Degree, DegreeLevel, EducationDegree}
import org.openurp.code.hr.model.{StaffType, WorkStatus}
import org.openurp.code.job.model.{ProfessionalTitle, TutorType}
import org.openurp.code.person.model.{Gender, IdType, Nation, PoliticalStatus}

import java.time.LocalDate

/**
 * 教职工信息
 */
class Staff extends LongId, Coded, Named, EnNamed, Updated, TemporalOn {

  /** 学校 */
  var school: School = _

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

  /** 电子邮件 */
  var email: Option[String] = None

  /** 联系手机 */
  var mobile: Option[String] = None

  /** 部门 */
  var department: Department = _

  /** 教职工类别 */
  var staffType: StaffType = _

  /** 导师类型 */
  var tutorType: Option[TutorType] = None

  /** 是否在编 */
  var formalHr: Boolean = _

  /** 最高职称 */
  var title: Option[ProfessionalTitle] = None

  /** 最高学历 */
  var educationDegree: Option[EducationDegree] = None

  /** 学位层次 */
  var degreeLevel: Option[DegreeLevel] = None

  /** 最高学位 */
  var degree: Option[Degree] = None

  /** 最高学位授予单位 */
  var degreeAwardBy: Option[String] = None

  /** 在职状态 */
  var status: WorkStatus = _

  /** 全职工作单位 */
  var organization: Option[String] = None

  /** 个人主页 */
  var homepage: Option[String] = None

  /** 是否兼职 */
  var parttime: Boolean = _

  /** 是否外聘 */
  var external: Boolean = _

  def description: String = {
    s"$code $name ${department.shortName.getOrElse(department.name)}"
  }
}
