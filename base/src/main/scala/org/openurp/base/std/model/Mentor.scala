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

package org.openurp.base.std.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Named, TemporalOn}
import org.openurp.base.model.*
import org.openurp.code.person.model.Gender

import scala.collection.mutable

/**
 * 辅导员
 */
class Mentor extends LongId with Named with TemporalOn {

  /** 教职工 */
  var staff: Staff = _

  /** 部门 */
  def department: Department = staff.department

  /** 工号 */
  def code: String = staff.code

  /** 性别 */
  def gender: Gender = staff.gender

  /** 项目列表 */
  var projects: mutable.Set[Project] = Collections.newSet[Project]

}
