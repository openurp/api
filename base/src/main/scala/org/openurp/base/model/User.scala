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

package org.openurp.base.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.*
import org.openurp.code.hr.model.UserCategory
import org.openurp.code.person.model.Gender

import java.time.Instant
import scala.collection.mutable

/**
 * 通用人员信息
 */
class User extends LongId, Coded, Named, EnNamed, Updated, Remark, TemporalOn {

  /** 学校 */
  var school: School = _

  /** 主用户组 */
  var group: Option[UserGroup] = None

  /** 其他用户组 */
  var groups: mutable.Set[UserGroupMember] = Collections.newSet[UserGroupMember]

  /** 性别 */
  var gender: Gender = _

  /** 部门 */
  var department: Department = _

  /** 类别 */
  var category: UserCategory = _

  /** 电子邮件 */
  var email: Option[String] = None

  /** 移动电话 */
  var mobile: Option[String] = None

  def description: String = {
    s"$code $name ${department.shortName.getOrElse(department.name)}"
  }

  def addGroup(g: UserGroup): Unit = {
    if !groups.exists(_.group == g) then
      val m = new UserGroupMember
      m.updatedAt = Instant.now
      m.group = g
      m.user = this
      groups.addOne(m)
  }

  def removeGroup(g: UserGroup): Unit = {
    groups.subtractAll(groups.find(_.group == g))
  }

  def addGroups(gs: collection.Seq[UserGroup]): Unit = {
    gs.headOption foreach { g =>
      this.group match
        case None =>
          this.group = Some(g)
          if gs.size > 1 then gs.tail foreach { x => this.addGroup(x) }
        case Some(pg) =>
          gs foreach { g => this.addGroup(g) }
    }
    this.group foreach { g => this.removeGroup(g) } //从附加用户组删除主组
  }
}
