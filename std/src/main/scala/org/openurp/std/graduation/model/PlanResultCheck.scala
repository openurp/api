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

package org.openurp.std.graduation.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.std.model.Student

/** 计划完成情况确认
 */
class PlanResultCheck extends LongId, Updated {

  /** 学生 */
  var std: Student = _

  /** 各个类别完成情况 */
  var contents: String = _

  /** 要求学分 */
  var requiredCredits: Float = _

  /** 通过学分 */
  var passedCredits: Float = _

  /** 欠学分 */
  var owedCredits: Float = _

  /** 预计通过后所欠学分 */
  var owedCredits2: Float = _

  /** 在读通过后所欠学分 */
  var owedCredits3: Float = _

  def this(std: Student) = {
    this()
    this.std = std
  }
}
