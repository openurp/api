/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.hr.base.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.TemporalOn
import org.openurp.code.job.model.{ DutyGrade, DutyType }

/**
 * 行政职务信息
 */
class DutyInfo extends LongId with TemporalOn {

  var staff: Staff = _

  /**职务名称*/
  var name: String = _

  /**职务类型*/
  var dutyType: DutyType = _

  /**职务等级*/
  var dutyGrade: DutyGrade = _

}

