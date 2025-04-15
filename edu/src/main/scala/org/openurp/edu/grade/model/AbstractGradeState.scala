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

package org.openurp.edu.grade.model

import org.beangle.data.model.LongId
import org.openurp.code.edu.model.GradingMode

/**
 * 成绩状态抽象基类
 *
 * @author chaostone
 */
abstract class AbstractGradeState extends LongId, GradeState {

  /** 成绩记录方式 */
  var gradingMode: GradingMode = _

  /** 成绩录入状态 */
  var status: Int = Grade.Status.New

  /** 操作者 */
  var operator: String = _

  /** 优秀率上限 */
  var excellentRatioLimit: Option[Float] = None

  /** 优秀率 */
  var excellentRatio: Option[Float] = None

  /**
   * 确认的和发布的全部算作确认过的
   */
  def confirmed: Boolean = status >= Grade.Status.Confirmed

  def published: Boolean = status == Grade.Status.Published

}
