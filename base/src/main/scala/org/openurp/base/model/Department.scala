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

import scala.collection.mutable

/**
 * 部门
 */
class Department extends IntId, Coded, Named, EnNamed, Hierarchical[Department]
  , TemporalOn, Updated, Remark {
  var school: School = _
  var shortName: Option[String] = None
  var category: Option[DepartmentCategory] = None
  var teaching: Boolean = _
  var research: Boolean = _
  /** 校区列表 */
  var campuses: mutable.Buffer[Campus] = new mutable.ListBuffer[Campus]

  def topDepartName: String = {
    parent match {
      case None => name
      case Some(p) =>
        //不能和学校同名
        if (p.name == school.name) name else p.topDepartName
    }
  }

}
