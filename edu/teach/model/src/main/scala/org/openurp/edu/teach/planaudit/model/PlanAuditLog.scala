/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
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
package org.openurp.edu.teach.planaudit.model

import java.sql.Date
import org.beangle.data.model.LongId
import org.openurp.edu.base.model.Student

//FIXME donot mapping
class PlanAuditLog extends LongId {

  var standardUsed: String = _

  var std: Student = _

  var auditBy: String = _

  var ip: String = _

  var operateAt: Date = _

  var passed: Boolean = _

  var detail: String = _

  def this(result: PlanAuditResult) {
    this
    std = result.std
    passed = result.passed
  }
}
