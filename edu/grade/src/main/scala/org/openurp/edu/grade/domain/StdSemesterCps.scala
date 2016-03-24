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
package org.openurp.edu.grade.domain

import org.beangle.data.model.LongId
import org.openurp.base.model.Semester

/**
 * 每学期绩点
 *
 * @author chaostone
 */
class StdSemesterCps extends LongId {

  /**
   * 学生绩点
   */
  var stdGpa: StdCps = _

  /**
   * 学期
   */
  var semester: Semester = _

  /**
   * 平均绩点
   */
  var gpa: Float = _

  /**
   * 平均分
   */
  var ga: Float = _

  /**
   * 获得学分
   */
  var credits: Float = _

  /**
   * 修读学分
   */
  var totalCredits: Float = _

  /**
   * 总成绩数量
   */
  var count: Int = 0

  def this(semester: Semester, gpa: java.lang.Float) {
    this()
    this.semester = semester
    this.gpa = gpa
  }
}
