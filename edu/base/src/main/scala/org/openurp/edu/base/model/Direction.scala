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
package org.openurp.edu.base.model

import scala.collection.mutable.Buffer

import org.beangle.data.model.{ Coded, LongId, Named, Remark, TemporalOn, Updated }
import org.beangle.data.model.annotation.code
import org.openurp.base.model.Department
import org.openurp.edu.base.code.model.Education

/**
 * 方向信息 专业领域.
 *
 * @author chaostone
 */
class Direction extends LongId with TemporalOn with Coded with Named with Updated with Remark {

  var project: Project = _
  /** 专业方向英文名 */
  var enName: Option[String] = None
  /** 所属专业 */
  var major: Major = _
  /** 部门 */
  var journals: Buffer[DirectionJournal] = new collection.mutable.ListBuffer[DirectionJournal]
}

class DirectionJournal extends LongId with TemporalOn with Remark {

  /**专业方向*/
  var direction: Direction = _
  /**培养层次*/
  var education: Education = _
  /**部门*/
  var depart: Department = _
}
