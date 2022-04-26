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

package org.openurp.prac.thesis.model

import org.beangle.data.model.LongId
import org.openurp.base.model.{Department, User}
import org.openurp.base.edu.model.TeachingOffice
import org.openurp.code.job.model.ProfessionalTitle

/** 指导教师 */
class Advisor extends LongId {

  var user: User = _

  var department: Department = _

  var title: Option[ProfessionalTitle] = None

  /** 所属教研室 */
  var office: Option[TeachingOffice] = None

  /** 是否教学副院长 */
  var subdecanal: Boolean = _

  /** 所带学生数 */
  var maxWriters: Long = _

  /** 教师简介 */
  var description: Option[String] = None

  /** 手机 */
  var mobile: Option[String] = None

  /** 邮箱 */
  var email: Option[String] = None

}
