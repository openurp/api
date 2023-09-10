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

package org.openurp.edu.extern.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.edu.model.Course
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{ExamStatus, GradingMode}
import org.openurp.edu.extern.code.CertificateSubject

import java.time.{LocalDate, YearMonth}
import scala.collection.mutable

/**
 * 校外证书成绩
 */
class CertificateGrade extends LongId with Updated {

  var std: Student = _

  var score: Option[Float] = None

  var scoreText: String = _

  var passed: Boolean = _

  var subject: CertificateSubject = _

  var certificate: String = _

  var examNo: Option[String] = None

  var acquiredOn: YearMonth = _

  var gradingMode: GradingMode = _

  var examStatus: ExamStatus = _

  var exempts: mutable.Set[Course] = Collections.newSet[Course]

  var status: Int = _
}
