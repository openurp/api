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
package org.openurp.people.info.model

import java.util.Date
import org.beangle.data.model.LongId
import org.openurp.people.base.model.Person
import org.beangle.data.model.TemporalOn
import org.openurp.code.job.model.ProfessionalTitle

class WorkHistory extends LongId with TemporalOn {

  var person: Person = _
  
  var workPlace: String = _
  
  var workContent: String = _
  
  /**专业技术职务*/
  var techPosition: ProfessionalTitle = _
  
  /**党政职务*/
  var adminPosition: String = _
  
  /**证明人*/
  var witness: String = _
  
  /**备注 */
  var remark: String = _
}