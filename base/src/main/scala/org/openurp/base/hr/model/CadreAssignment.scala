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

package org.openurp.base.hr.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.TemporalOn
import org.openurp.base.model.Department
import org.openurp.code.hr.model.CadrePostRank

/** 干部任职信息
 */
class CadreAssignment extends LongId, TemporalOn {

  /** 教职工 */
  var staff: Staff = _

  /** 部门 */
  var department: Department = _

  /** 是否兼职 */
  var concurrent: Boolean = _

  /** 是否正职 */
  var principal: Boolean = _

  /** 职级 */
  var rank: CadrePostRank = _

  /** 行政职务 */
  var post: String = _

}
