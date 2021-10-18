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

package org.openurp.edu.clazz.model

import org.beangle.commons.lang.time.WeekState
import org.beangle.data.model.Component
import org.beangle.data.model.LongId
import org.openurp.code.edu.model.ClassroomType

/**
 * 课程安排
 */
class Schedule extends Component with Serializable with Cloneable {

  /** 要求排课课时 */
  var creditHours: Int = _

  /** 周状态 */
  var weekstate: WeekState = _

  /** 具体排课结果 */
  var sessions: collection.mutable.Set[Session] = _

  /** 教室类型 */
  var roomType: ClassroomType = _

  /** 起始周 */
  def firstWeek: Int = {
    if (null != weekstate) weekstate.first else 0
  }
  /** 结束周 */
  def lastWeek: Int = {
    if (null != weekstate) weekstate.last else 0
  }

}
