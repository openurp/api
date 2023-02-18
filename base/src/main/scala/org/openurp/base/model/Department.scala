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

package org.openurp.base.model

import org.beangle.data.model.IntId
import org.beangle.data.model.pojo.*
import org.openurp.code.hr.model.DepartmentCategory
import org.openurp.code.edu.model.Institution

import scala.collection.mutable.{Buffer, ListBuffer}

/**
 * 部门
 */
class Department extends IntId with Coded with Named with Hierarchical[Department]
  with TemporalOn with Updated with Remark {
  var school: School = _
  var enName: Option[String] = None
  var shortName: Option[String] = None
  var category: Option[DepartmentCategory] = None
  var teaching: Boolean = _
  var research: Boolean = _
  /** 校区列表 */
  var campuses: Buffer[Campus] = new ListBuffer[Campus]
}
