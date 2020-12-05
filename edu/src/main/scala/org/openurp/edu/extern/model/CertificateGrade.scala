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

import java.time.LocalDate

import org.beangle.data.model.LongId
import org.openurp.code.edu.model.ExamStatus
import org.openurp.code.edu.model.GradingMode
import org.openurp.base.edu.model.{Course, Student}
import org.openurp.edu.extern.code.model.CertificateSubject
import org.beangle.commons.collection.Collections
import org.beangle.data.model.pojo.Updated

import scala.collection.mutable

/**
 * 校外证书成绩
 */
class CertificateGrade extends LongId with Updated{

  var std: Student = _

  var score: Option[Float] = None

  var scoreText: String = _

  var passed: Boolean = _

  var subject: CertificateSubject = _

  var certificate: Option[String] = None

  var examNo: Option[String] = None

  var acquiredOn: LocalDate = _

  var gradingMode: GradingMode = _

  var examStatus: ExamStatus = _

  var courses: mutable.Set[Course] = Collections.newSet[Course]

  var status: Int = _
}
