/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.model

import org.beangle.data.model.{LongId, Named, Updated}
import org.openurp.base.model.Room
import org.openurp.code.edu.model.ClassroomType
/**
 * 教室
 */
class Classroom extends LongId with Named with Updated {

  /**
   * 房间
   */
  var room: Room = _

  /**
   * 英文名
   */
  var enName: String = _

  /**
   * 简称
   */
  var shortName: String = _

  /**
   * 教室类型
   */
  var classroomType: ClassroomType = _

  /**
   * 上课容量
   */
  var courseCapacity: Int = _

  /**
   * 考试容量
   */
  var examCapacity: Int = _

}