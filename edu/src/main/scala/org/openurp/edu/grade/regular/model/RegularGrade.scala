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

package org.openurp.edu.grade.regular.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.edu.model.Student
import org.openurp.edu.clazz.model.Clazz

import scala.collection.mutable

/** 平时总评成绩
 *
 */
class RegularGrade extends LongId with Updated {
  var clazz: Clazz = _

  var std: Student = _

  var score: Float = _

  var tests: mutable.Buffer[RegularTestGrade] = Collections.newBuffer[RegularTestGrade]

  var status: Int = _

  def getTestGrade(gt: RegularTestType): Option[RegularTestGrade] = {
    tests.find(_.testType == gt)
  }
}
