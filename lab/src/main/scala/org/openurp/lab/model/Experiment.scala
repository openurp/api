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

package org.openurp.lab.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, Named}
import org.openurp.base.edu.model.Course
import org.openurp.base.model.{ProjectBased, Semester}
import org.openurp.code.edu.model.{EducationLevel, ExperimentCategory, ExperimentType, Level1Discipline}

import scala.collection.mutable

/** 实验项目
 */
@beta
class Experiment extends LongId, Coded, Named, ProjectBased {
  /** 学年学期 */
  var semester: Semester = _
  /** 课程 */
  var course: Course = _
  /** 实验类别 */
  var category: ExperimentCategory = _
  /** 实验类型 */
  var experimentType: ExperimentType = _
  /** 一级学科 */
  var discipline: Level1Discipline = _
  /** 对应培养层次 */
  var level: EducationLevel = _
  /** 是否在线实验 */
  var online: Boolean = _
  /** 学时 */
  var creditHours: Int = _
  /** 每组人数 */
  var groupStdCount: Int = _
  /** 具体排课结果 */
  var activities: mutable.Set[ExperimentActivity] = Collections.newSet[ExperimentActivity]

}
