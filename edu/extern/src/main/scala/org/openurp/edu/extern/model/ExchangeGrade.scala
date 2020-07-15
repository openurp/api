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

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.edu.base.model.Course

import scala.collection.mutable

/**
 * 外校成绩
 */
class ExchangeGrade extends LongId with Remark with Updated {

  var exchangeStudent: ExchangeStudent = _

  var courseName: String = _

  var credits: Float = _

  var acquiredOn: LocalDate = _

  var scoreText: String = _

  var courses: mutable.Set[Course] = Collections.newSet[Course]
}
