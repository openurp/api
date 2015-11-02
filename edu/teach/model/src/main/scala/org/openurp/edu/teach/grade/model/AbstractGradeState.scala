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
package org.openurp.edu.teach.grade.model

import org.beangle.data.model.LongId
import org.openurp.edu.base.code.model.ScoreMarkStyle

/**
 * 成绩状态抽象基类
 *
 * @author chaostone
 */
abstract class AbstractGradeState extends LongId with GradeState {

  /**
   * 成绩记录方式
   */
  var markStyle: ScoreMarkStyle = _

  /**
   * 成绩录入状态
   */
  var status: Int = Grade.Status.New

  /**
   * 操作者
   */
  var operator: String = _

  /**
   * 确认的和发布的全部算作确认过的
   */
  def confirmed: Boolean = status >= Grade.Status.Confirmed

  def published: Boolean = status == Grade.Status.Published

}
