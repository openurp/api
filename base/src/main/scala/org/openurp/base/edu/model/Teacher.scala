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

package org.openurp.base.edu.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, TemporalOn, Updated}
import org.openurp.base.edu.code.TeacherType
import org.openurp.base.model.{Department, Person, Project, School, User}
import org.openurp.code.edu.model.{Degree, EducationDegree}
import org.openurp.code.hr.model.WorkStatus
import org.openurp.code.job.model.ProfessionalTitle

import scala.collection.mutable

/**
 * 教师信息
 */
class Teacher extends LongId with Updated with TemporalOn with Remark {

  /** 学校 */
  var school: School = _

  /** 用户 */
  var user: User = _

  /** 所在教学部门 */
  var department: Department = _

  /** 人员信息 */
  var person: Option[Person] = None

  /** 项目列表 */
  var projects: mutable.Set[Project] = Collections.newSet[Project]

  /** 教师类型 */
  var teacherType: TeacherType = _

  /** 在职状态 */
  var status: WorkStatus = _

  /** 最高职称 */
  var title: Option[ProfessionalTitle] = None

  /** 最高学历 */
  var educationDegree: Option[EducationDegree] = None

  /** 最高学位 */
  var degree: Option[Degree] = None

  /** 是否在编 */
  var formalHr: Boolean = _

}
