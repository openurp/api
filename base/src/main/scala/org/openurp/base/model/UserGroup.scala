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
import org.beangle.data.model.IntId
import org.beangle.data.model.pojo.*

/** 用户组
 */
class UserGroup extends IntId, Named, Coded, Enabled, Hierarchical[UserGroup], Remark {

  var school: School = _

  var manager: Option[User] = None

  def isParentOf(p: UserGroup): Boolean = {
    val pts = Collections.newSet[UserGroup]
    var pt: UserGroup = p
    while (null != pt && !pts.contains(pt) && !pts.contains(this)) {
      pts.add(pt)
      pt = pt.parent.orNull
    }
    pts.contains(this)
  }

  /** 自动管理，无需手动增加成员 */
  var autoManage: Boolean = _
}
