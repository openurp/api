/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Coded
import org.beangle.data.model.pojo.Named
import org.beangle.data.model.pojo.TemporalOn
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.Campus
import org.openurp.base.model.Room
import org.openurp.code.edu.model.ClassroomType

/**
 * 教室
 */
class Classroom extends LongId with Named with Coded with Updated with TemporalOn {

  /**项目*/
  var project: Project = _
  /**
   * 房间
   * 可以为空，表示虚拟房间
   */
  var room: Option[Room] = None

  /**所属校区*/
  var campus: Campus = _

  /**
   * 英文名
   */
  var enName: Option[String] = None

  /**
   * 简称
   */
  var shortName: Option[String] = None

  /**
   * 教室类型
   */
  var roomType: ClassroomType = _

  /**
   * 上课容量
   */
  var courseCapacity: Int = _

  /**
   * 考试容量
   */
  var examCapacity: Int = _

}
