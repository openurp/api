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

package org.openurp.edu.grade.config

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config

/**
 * 成绩分级配置项
 */
@config
class GradeRateItem extends LongId {

  /** 成绩配置 */
  var config: GradeRateConfig = _

  /** 最低分 */
  var minScore: Float = _

  /** 最高分 */
  var maxScore: Float = _

  /** 绩点表达式 */
  var gpExp: Option[String] = None

  /** 显示名称 */
  var grade: Option[String] = None

  /** 默认分数 */
  var defaultScore: Option[Float] = None

  def contains(f: Float): Boolean = {
    minScore <= f && f <= maxScore
  }
}
