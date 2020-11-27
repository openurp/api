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

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, Named, TemporalOn, Updated}
import org.openurp.base.model.{Department, User}

import scala.collection.mutable

/** 教研室 */
class TeachingGroup extends LongId with Coded with Named with Updated with TemporalOn {

  /** 项目 */
  var project: Project = _

  /** 部门 */
  var department: Department = _

  /** 负责人 */
  var director: Option[User] = None

  /** 成员 */
  var members: mutable.Buffer[User] = Collections.newBuffer[User]
}
