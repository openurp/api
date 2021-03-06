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
package org.openurp.edu.clazz.model

import org.beangle.data.model.LongId

/**
 * 选课限制条件项
 */
class RestrictionItem extends LongId with Cloneable {

  /** 限制具体项目 */
  var meta: RestrictionMeta.Meta = _

  /** 所在限制组 */
  var restriction: Restriction = _

  /**
   * 是否包含限定内容
   *  不包含exclusive情况下，为排除限定内容
   */
  var includeIn: Boolean = _

  /** 限制内容 */
  var contents: String = _
}
