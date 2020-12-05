/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
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
package org.openurp.edu.extern.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{DateRange, Updated}
import org.openurp.code.edu.model.{EduCategory, EducationLevel}
import org.openurp.base.edu.AuditStates
import org.openurp.base.edu.model.Student

import scala.collection.mutable;

/**
 * 外部学习经历
 */
class ExchangeStudent extends LongId with Updated with DateRange {
  var std: Student = null
  var school: ExchangeSchool = null
  var majorName: Option[String] = None
  var level: EducationLevel = null
  var category: EduCategory = null
  var grades: mutable.Buffer[ExchangeGrade] = Collections.newBuffer[ExchangeGrade]
  var transcriptPath: Option[String] = None
  /** 审核状态 */
  var auditState: AuditStates.State = AuditStates.Draft
  var auditOpinion: Option[String] = None
  var credits: Float = _
  var exemptionCredits: Float = _
}
