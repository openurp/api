/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright (c) 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.hr.base.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.TemporalOn
import org.openurp.code.job.model.ProfessionalTitle

/**工作经历信息*/
class WorkInfo extends LongId with TemporalOn {

  /**教职工*/
  var staff: Staff = _

  /**工作地点*/
  var workPlace: String = _

  /**工作内容*/
  var workContent: String = _

  /**专业技术职务*/
  var title: ProfessionalTitle = _

  /**党政职务*/
  var adminPosition: String = _

  /**证明人*/
  var witness: String = _

  /**备注 */
  var remark: String = _
}
