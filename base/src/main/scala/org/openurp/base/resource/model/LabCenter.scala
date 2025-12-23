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

package org.openurp.base.resource.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Named, TemporalOn}
import org.openurp.base.model.{Department, School}

import scala.collection.mutable

/** 实验中心
 * 囊括了部分院系和实验室
 */
class LabCenter extends LongId, Named, TemporalOn {
  /** 所属学校 */
  var school: School = _
  /** 关联部门 */
  var departs: mutable.Set[Department] = Collections.newSet[Department]
  /**简称*/
  var shortName: Option[String] = None
}
