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

package org.openurp.trd.achievement.model

import org.beangle.data.model.LongId
import org.openurp.base.model.User

/** 项目成员
 *
 */
class RdAchievementMember extends LongId {
  /** 排名 */
  var idx: Int = _
  /** 姓名 */
  var name: String = _
  /** 参与人 */
  var user: Option[User] = None
  /** 项目 */
  var achievement: RdAchievement = _
}
