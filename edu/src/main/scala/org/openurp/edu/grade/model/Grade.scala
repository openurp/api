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

import org.beangle.commons.lang.Objects
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{GradeType, GradingMode}

import java.time.Instant

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
 * @author chaostone
 * @since 2006
 */
trait Grade extends LongId, Ordered[Grade], Updated {

  var createdAt: Instant = _

  def score: Option[Float]

  def score_=(s: Option[Float]): Unit

  def std: Student

  def scoreText: Option[String]

  def scoreText_=(st: Option[String]): Unit

  def passed: Boolean

  def passed_=(dp: Boolean): Unit

  @transient
  def published: Boolean = {
    status == Grade.Status.Published
  }

  @transient
  def confirmed: Boolean = {
    status == Grade.Status.Published || status == Grade.Status.Confirmed
  }

  def status: Int

  def status_=(newStatus: Int): Unit

  def gradingMode: GradingMode

  def gradingMode_=(style: GradingMode): Unit

  def gradeType: GradeType

  def operator: Option[String]

  def operator_=(o: Option[String]): Unit

  // 同一个学生，成绩好的放前面
  override def compare(grade: Grade): Int = {
    Objects.compareBuilder.add(this.std.id, grade.std.id).add(grade.score.orNull, this.score.orNull).build()
  }
}
