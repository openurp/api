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
import org.openurp.base.edu.model.Teacher
import org.openurp.base.model.{Department, Staff}
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
  var staff: Staff = _

  /** 个人简介 */
  var intro: String = _

  /** 教学工作经历 */
  var career: Option[String] = None

  /** 科研成果 */
  var harvest: Option[String] = None

  /** 研究方向 */
  var research: Option[String] = None

  /** 学术兼职 */
  var titles: Option[String] = None

  /** 联系方式 */
  var contact: Option[String] = None

}
