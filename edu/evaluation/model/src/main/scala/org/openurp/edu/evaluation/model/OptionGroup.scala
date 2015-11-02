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
package org.openurp.edu.evaluation.model

import org.beangle.data.model.LongId
import org.openurp.base.model.Department
import org.beangle.data.model.Named
import scala.collection.mutable.HashSet
/**
 * 选项组
 *
 * @author chaostone
 */
class OptionGroup extends LongId with Named {
  /** 倾向性权重 必须在0和1之间 */
  var oppoVal: Float = _

  /** 各类选项 */
  var options: HashSet[Option] = new collection.mutable.HashSet[Option]

  /** 创建部门 */
  var depart: Department = _
}