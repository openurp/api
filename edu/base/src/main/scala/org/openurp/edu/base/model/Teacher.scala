/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.model

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.code
import org.beangle.data.model.pojo.TemporalOn
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.Person
import org.openurp.base.model.User
import org.openurp.code.hr.model.WorkStatus
import org.openurp.code.job.model.ProfessionalTitle
import org.openurp.edu.base.code.model.TeacherType

/**
 * 教师信息
 */
class Teacher extends LongId with Updated with TemporalOn {

  /**用户*/
  var user: User = _

  /**人员信息*/
  var person: Option[Person] = None

  /**所在项目*/
  var project: Project = _

  /**教师类型*/
  var teacherType: TeacherType = _

  /**在职状态*/
  var status: WorkStatus = _

  /**职称*/
  var title: Option[ProfessionalTitle] = None

  /**是否兼职*/
  var parttime: Boolean = _

  /**是否退休返聘*/
  var retired: Boolean = _

  /**是否在编*/
  var formalHr: Boolean = _

  /**备注*/
  var remark: Option[String] = None
}
