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

package org.openurp.edu.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId

/**
 * 课程限制条件组
 */
class Restriction extends LongId with Cloneable {

  /** 教学任务 */
  var clazz: Clazz = _

  /**是否主要开课对象*/
  var prime: Boolean = _

  /** 条件列表 */
  var items = Collections.newBuffer[RestrictionItem]

  /** 最大人数 */
  var maxCount: Int = _

  /** 当前人数 */
  var curCount: Int = _

  /** 父级菜单 */
  var parent: Option[Restriction] = None

  var children = Collections.newBuffer[Restriction]
}
