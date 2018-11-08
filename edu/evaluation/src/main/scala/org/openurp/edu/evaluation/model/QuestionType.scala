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
package org.openurp.edu.evaluation.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{ Named, Remark, TemporalOn, Updated }
import org.openurp.edu.base.model.Project

/**
 * 问题类型
 *
 * @author cwx,chaostone
 *
 */
class QuestionType extends LongId with Named with Updated with TemporalOn with Remark {
  var project: Project = _
  /** 英文名称 */
  var enName: scala.Option[String] = None
  /** 优先级 ,越大越靠前 */
  var priority: Int = _
  /** 状态 */
  var state: Boolean = false
}
