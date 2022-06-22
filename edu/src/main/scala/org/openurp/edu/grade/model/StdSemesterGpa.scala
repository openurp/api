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
import org.openurp.base.model.Semester

/**
 * 每学期绩点
 *
 * @author chaostone
 */
class StdSemesterGpa extends LongId with GpaStat {

  /**
   * 学生绩点
   */
  var stdGpa: StdGpa = _

  /**
   * 学期
   */
  var semester: Semester = _

  def this(semester: Semester, gpa: Float) = {
    this()
    this.semester = semester
    this.gpa = gpa
  }
}
