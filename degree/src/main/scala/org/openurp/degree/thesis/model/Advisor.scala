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

package org.openurp.degree.thesis.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.TemporalOn
import org.openurp.base.edu.model.TeachingOffice
import org.openurp.base.model.{Department, Project, Staff, User}
import org.openurp.code.job.model.ProfessionalTitle

import scala.collection.mutable

/** 指导教师 */
class Advisor extends LongId, TemporalOn {

  var project: Project = _

  var user: User = _

  var departs: mutable.Buffer[Department] = Collections.newBuffer[Department]

  var title: Option[ProfessionalTitle] = None

  /** 所属教研室 */
  var office: Option[TeachingOffice] = None

  /** 所带学生数 */
  var maxWriters: Long = _

  var mobile: Option[String] = None

  var email: Option[String] = None
  /** 教师简介 */
  var description: Option[String] = None
}
