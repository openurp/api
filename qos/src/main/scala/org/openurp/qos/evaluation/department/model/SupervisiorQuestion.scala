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

package org.openurp.qos.evaluation.department.model

import org.beangle.data.model.LongId
import org.openurp.qos.evaluation.config.Question

/**
 * 院系问题评教结果
 *
 * @author chaostone
 */
class SupervisiorQuestion extends LongId {
  /** 问题 */
  var question: Question = _
  /** 得分 */
  var score: Float = _
  /** 评教结果 */
  var result: SupervisiorEvaluate = _
}
