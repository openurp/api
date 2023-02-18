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

package org.openurp.prac.innovation.model

import org.beangle.data.model.pojo.{DateRange, Named}
import org.beangle.data.model.IntId
import org.beangle.commons.collection.Collections

import org.openurp.base.model.School

import scala.collection.mutable

/** 批次 */
class Batch extends IntId with Named with DateRange {

  var school: School = _

  /** 是否归档 */
  var archived: Boolean = _

  /**阶段*/
  var stages: mutable.Buffer[Stage] = Collections.newBuffer[Stage]

  def getStage(stageType: StageType): Option[Stage] = {
    stages.find(_.stageType == stageType)
  }

}
