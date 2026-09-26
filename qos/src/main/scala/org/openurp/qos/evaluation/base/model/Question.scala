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

package org.openurp.qos.evaluation.base.model

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.{Remark, TemporalOn, Updatable}
import org.openurp.base.model.{Department, Project}
import scala.compiletime.uninitialized

/**
 * 评教问题
 *
 * @author chaostone
 */
class Question extends LongId, Updatable, TemporalOn {
  var project: Project = uninitialized
  /** 问题内容 */
  var contents: String = uninitialized
  /** 问题类型 */
  var indicator: Indicator = uninitialized
  /** 分值 */
  var score: Float = uninitialized
  /** 优先级 */
  var priority: Int = uninitialized
  /** 是否附加题 */
  var addition: Boolean = false
  /** 选项组 */
  var optionGroup: OptionGroup = uninitialized
}
