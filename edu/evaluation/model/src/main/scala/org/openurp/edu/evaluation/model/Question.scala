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
package org.openurp.edu.evaluation.model

import org.beangle.data.model.{ TemporalOn, Updated }
import org.beangle.data.model.LongId
import org.openurp.base.model.Department
/**
 * 评教问题
 *
 * @author chaostone
 */
class Question extends LongId with Updated with TemporalOn {
  /** 问题内容 */
  var content: String = _
  /** 问题类型 */
  var questionType: QuestionType = _
  /** 分值 */
  var score: Float = _
  /** 问题所对应的使用部门 */
  var depart: Department = _
  /** 优先级 */
  var priority: Int = _
  /** 注释 */
  var remark: String = _
  /** 使用状态 */
  var state: Boolean = false
  /** 是否附加题 */
  var addition: Boolean = false
  /** 选项组 */
  var optionGroup: OptionGroup = _
}
