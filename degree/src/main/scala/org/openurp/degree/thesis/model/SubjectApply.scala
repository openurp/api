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

package org.openurp.degree.thesis.model

import org.beangle.data.model.LongId
import org.openurp.base.std.model.GraduateGrade

object SubjectApply {
  val Round1 = 1
  val Round2 = 2
  val Final = 99
}

/** 开题选择
 * 记录学生初次开题和补选开题的题目
 */
class SubjectApply extends LongId {

  def this(writer: Writer, last: Subject) = {
    this()
    this.writer = writer
    this.last = Some(last)
  }

  /** 学生 */
  var writer: Writer = _

  /** 当前轮次，默认是初选 */
  var currentRound: Int = 1

  /** 初次选题 */
  var first: Option[Subject] = None

  /** 补选情况 */
  var second: Option[Subject] = None

  /** 最终选题 */
  var last: Option[Subject] = None

}
