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
package org.openurp.edu.grade.model

import org.beangle.data.model.annotation.config
import org.beangle.data.model.LongId
/**
 * 成绩分级配置项
 */
@config
class GradeRateItem extends LongId {

  /**
   * 成绩配置
   */
  var config: GradeRateConfig = _

  /**
   * 显示名称
   */
  var grade: String = _

  /**
   * 最低分
   */
  var minScore: Float = _

  /**
   * 最高分
   */
  var maxScore: Float = _

  /**
   * 绩点表达式
   */
  var gpExp: Option[String] = None

  /**
   * 默认分数
   */
  var defaultScore: java.lang.Float = _

  def contains(f: java.lang.Float): Boolean = {
    minScore <= f.floatValue() && f.floatValue() <= maxScore
  }

  def inScope(score: java.lang.Float): Boolean = {
    maxScore.compareTo(score) > -1 && minScore.compareTo(score) < 1
  }
}
