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

package org.openurp.edu.exam.config

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.Named
import org.openurp.base.model.ProjectBased
import org.openurp.code.edu.model.ExamType

/** 考试分配设定
 */
@config
class ExamAllocSetting extends LongId with Named with ProjectBased {
  var examType: ExamType = _
  /**考场分配人数占容量的最小比例*/
  var minOccupyRatio: Float = _
  /**考场容量的下限*/
  var minCapacity: Int = _
  /**考场分配策略*/
  var allocPolicy: RoomAllocPolicy = _
  /**考生连续考试的最小间隔(按小时计算)*/
  var minStdExamInterval: Int = _
}
