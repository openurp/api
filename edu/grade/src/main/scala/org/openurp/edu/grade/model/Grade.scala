/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.edu.base.code.model.{ GradeType, ScoreMarkStyle }
import org.openurp.edu.base.model.Student

object Grade {
  object Status {
    val New = 0
    val Confirmed = 1
    val Published = 2
  }
}

/**
 * 成绩接口
 * </p>
 * 系统中所有成绩的抽象接口，记录成绩的原始分数、呈现方式、状态和是否通过。
 * 成绩中设立原始分数和分数字面值，分别表示数字型分数和文字型评价。分数用于和其他记录方式进行转换，
 * 分数字面值则为最后的转换结果。
 *
 * @depend - - - ScoreMarkStyle
 * @author chaostone
 * @since 2006
 */
trait Grade extends LongId with Ordered[Grade] with Updated {
  def score: Option[Float]

  def score_=(s: Option[Float])

  def std: Student

  def scoreText: String

  def scoreText_=(st: String)

  def passed: Boolean

  def passed_=(dp: Boolean)

  def published: Boolean = {
    status == Grade.Status.Published
  }

  def confirmed: Boolean = {
    status == Grade.Status.Published || status == Grade.Status.Confirmed
  }
  def status: Int

  def status_=(newStatus: Int)

  def markStyle: ScoreMarkStyle

  def markStyle_=(style: ScoreMarkStyle)

  def gradeType: GradeType

  def operator: String

  def operator_=(o: String)
}
