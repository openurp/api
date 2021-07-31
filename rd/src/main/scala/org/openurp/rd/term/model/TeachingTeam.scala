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
package org.openurp.rd.term.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Named, Updated}
import org.openurp.base.model.{Department, User}
import org.openurp.rd.code.model.RdLevel

import scala.collection.mutable

/** 教学团队
 *
 */
class TeachingTeam extends LongId with Named with Updated {

  /** 所在学院 */
  var department: Department = _

  /** 级别 */
  var level: RdLevel = _

  /** 带头人 */
  var leaders: mutable.Buffer[User] = Collections.newBuffer[User]

  /** 参与人 */
  var members: mutable.Buffer[TeachingTeamMember] = Collections.newBuffer[TeachingTeamMember]
  /** 获奖信息 */
  var awardTitle: Option[String] = None

}
