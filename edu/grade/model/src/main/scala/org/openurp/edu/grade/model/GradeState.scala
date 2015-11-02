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

import org.beangle.data.model.{ LongId, TemporalOn, Updated }
import org.openurp.edu.grade.code.model.GradeType
import org.openurp.edu.base.code.model.ScoreMarkStyle

/**
 * 成绩状态表<br>
 *
 * <pre>
 * 记录了对应教学任务成绩
 * 1)记录方式,
 * 2)各种成绩成分的百分比,
 * 3)各种成绩的确认状态,
 * 4)各种成绩的发布状态
 * </pre>
 *
 * @author 塞外狂人,chaostone
 */
trait GradeState extends LongId with TemporalOn with Updated {

  /**
   * 成绩类型
   */
  def gradeType: GradeType

  /**
   * 记录方式
   */
  def markStyle: ScoreMarkStyle

  /**
   * 记录方式
   */
  def markStyle_=(style: ScoreMarkStyle)

  /**
   * 是否提交确认
   */
  def confirmed: Boolean

  /**
   * 是否发布
   */
  def published: Boolean

  /**
   * 返回状态
   */
  def status: Int

  /**
   * 返回操作者
   */
  def operator: String
}
