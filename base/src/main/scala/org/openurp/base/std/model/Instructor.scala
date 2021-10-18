/*
 * Copyright (C) 2005, The OpenURP Software.
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

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{TemporalOn, Updated}
import org.openurp.base.edu.model.Project
import org.openurp.base.model.{School, User}

/**
 * 辅导员
 */
class Instructor extends LongId with Updated with TemporalOn {

  /** 学校 */
  var school: School = _
  /** 人员信息 */
  var user: User = _

}
