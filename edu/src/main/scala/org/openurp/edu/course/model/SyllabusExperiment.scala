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
import org.openurp.base.edu.model.Experiment
import scala.compiletime.uninitialized

/** 课程大纲中的实验
 */
class SyllabusExperiment extends LongId {

  var syllabus: Syllabus = uninitialized

  /** 序号(从1开始) */
  var idx: Int = uninitialized

  /** 实验 */
  var experiment: Experiment = uninitialized

  def this(syllabus: Syllabus, idx: Int, experiment: Experiment) = {
    this()
    this.syllabus = syllabus
    this.idx = idx
    this.experiment = experiment
  }
}
