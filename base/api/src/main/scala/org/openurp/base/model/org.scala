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
package org.openurp.base.model

import org.beangle.data.model.{ Coded, Hierarchical, IntId, Named, Remark, TemporalOn, Updated }
import org.beangle.data.model.annotation.code
import org.openurp.base.code.model.DepartmentCategory
import org.openurp.code.edu.model.Institution
/**
 * 学校
 */
class School extends IntId with Coded with Named {
  var institution: Institution = _
  var logoUrl: String = _
  var shortName: String = _
}
/**
 * 部门
 */
class Department extends IntId with Coded with Named with Hierarchical[Department]
    with TemporalOn with Updated with Remark {
  var school: School =_
  var enName: String = _
  var shortName: String = _
  var category: DepartmentCategory = _
  var teaching: Boolean = _
  var research: Boolean = _
}
