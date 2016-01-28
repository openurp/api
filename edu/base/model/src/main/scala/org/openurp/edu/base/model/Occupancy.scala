/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.model

import org.beangle.commons.lang.time.WeekTime
import org.beangle.data.model.{ LongId, Updated }
import org.openurp.code.edu.model.ClassroomUsage
import org.openurp.base.model.Room

/**
 * 房间占用情况
 * 这里不用classroom的原因，在于跨项目的教室冲突检测需要。
 */
class Occupancy extends LongId with Updated {

  /** 房间 */
  var room: Room = _ // 教室/考场/活动场地

  /** 时间 */
  var time = new WeekTime // 时间安排

  /** 用途 */
  var usage: ClassroomUsage = _

  /** 使用者 */
  var userid: String = _

  /** 说明 */
  var comments: String = _
}
