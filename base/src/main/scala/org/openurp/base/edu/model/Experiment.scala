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

package org.openurp.base.edu.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.*
import org.openurp.code.edu.model.{ExperimentCategory, ExperimentType, Level1Discipline}

/** 课程实验库
 */
class Experiment extends LongId, Coded, Named, EnNamed, Updated, TemporalOn, Remark {

  /** 课程 */
  var course: Course = _

  /** 实验类别 */
  var category: ExperimentCategory = _

  /** 一级学科 */
  var discipline: Level1Discipline = _

  /** 是否在线实验 */
  var online: Boolean = _

  /** 学时 */
  var creditHours: Float = _

  /** 实验类型 */
  var experimentType: ExperimentType = _

  /** 每组人数 */
  var groupStdCount: Int = _

  def this(course: Course) = {
    this()
    this.course = course
  }

  def this(course: Course, name: String, creditHours: Float, experimentType: ExperimentType, online: Boolean) = {
    this()
    this.course = course
    this.name = name
    this.creditHours = creditHours
    this.experimentType = experimentType
    this.online = online
  }

  def description: String = {
    s"${this.code} ${this.name} ${this.experimentType.name} ${this.creditHours}学时"
  }
}
