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

package org.openurp.lab.experiment.model

import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Coded
import org.openurp.base.edu.model.Experiment

/** 实验项目对应的课程
 */
@beta
class LabExperiment extends LongId {
  /** 序号(从1开始) */
  var idx: Int = _
  /** 修订任务 */
  var task: LabTask = _
  /** 实验 */
  var experiment: Experiment = _

  def this(idx: Int, task: LabTask, experiment: Experiment) = {
    this()
    this.idx = idx
    this.task = task
    this.experiment = experiment
  }
}
