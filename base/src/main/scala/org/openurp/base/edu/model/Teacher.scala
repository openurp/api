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
import org.beangle.data.model.pojo.*
import org.openurp.base.model.*
import org.openurp.code.job.model.{ProfessionalTitle, TutorType}
import org.openurp.code.person.model.Gender

import scala.collection.mutable

/**
 * 教师信息
 */
class Teacher extends LongId with TemporalOn with Named with Remark {

  /** 教职工 */
  var staff: Staff = _

  /** 用户 */
  var user: User = _

  /** 所在教学部门 */
  var department: Department = _

  /** 项目列表 */
  var projects: mutable.Set[Project] = Collections.newSet[Project]

  /** 任教校区 */
  var campuses: mutable.Set[Campus] = Collections.newSet[Campus]

  /** 导师类型 */
  var tutorType: Option[TutorType] = None

  /** 教师资格证编号 */
  var tqcNumber: Option[String] = None

  /** 其他职业资格证书和等级说明 */
  var oqc: Option[String] = None

  /** 所在教研室 */
  var office: Option[TeachingOffice] = None

  /** 工号 */
  def code: String = staff.code

  /** 性别 */
  def gender: Gender = staff.gender

  def title: Option[ProfessionalTitle] = staff.title

  def description: String = s"$code $name ${department.shortName.getOrElse(department.name)}"
}
