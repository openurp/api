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

package org.openurp.edu.course.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Named
import org.openurp.code.edu.model.ExperimentType

/** 课程大纲中的实验
 */
class SyllabusExperiment extends LongId, Named {

  var syllabus: Syllabus = _

  /** 顺序号 */
  var idx: Int = _

  /** 是否在线实验 */
  var online: Boolean = _

  /** 学时 */
  var creditHours: Int = _

  /** 实验类型 */
  var experimentType: ExperimentType = _

  def this(syllabus: Syllabus, idx: Int, name: String, creditHours: Int, experimentType: ExperimentType, online: Boolean) = {
    this()
    this.syllabus = syllabus
    this.idx = idx
    this.name = name
    this.creditHours = creditHours
    this.experimentType = experimentType
    this.online = online
  }
}
