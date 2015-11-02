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
package org.openurp.hr.info.model

import org.beangle.data.model.TemporalOn
import org.beangle.data.model.LongId
import org.openurp.hr.base.model.Staff
import org.openurp.code.hr.model.EmployType
import org.openurp.code.hr.model.StaffSourceType
import java.sql.Date

/**
 * 教职工来源信息
 * 教育部标准JY/T 1001 6.4.6
 */
class SourceInfo extends LongId {

  var staff: Staff = _

  /**教职工来源*/
  var sourceType: StaffSourceType = _

  /**参加工作日期*/
  var workStartOn: Date = _

  /**来校日期*/
  var employOn: Date = _

  /**用人形式*/
  var employType: EmployType = _

}