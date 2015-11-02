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
package org.openurp.edu.grade.audit.model

import org.beangle.data.model.Component
import org.openurp.edu.base.model.Course

class AuditStat extends Component {

  var creditsRequired: Float = _

  var creditsCompleted: Float = _

  var numRequired: Int = _

  var numCompleted: Int = _

  var passedCourses = new collection.mutable.HashSet[Course]

  var creditsConverted: Float = _

  def this(creditCompleted: Float, totalNum: Int) {
    this
    this.creditsCompleted = creditCompleted
    this.numCompleted = totalNum
  }

  def addCredits(credits: Float) {
    this.creditsCompleted += credits
  }

  def addNum(num: Int) {
    this.numCompleted += num
  }

  def passed: Boolean = {
    creditsRequired <= (creditsCompleted + creditsConverted) &&
      numRequired <= numCompleted
  }

  def creditNeeded(returnNegative: Boolean): Float = {
    val needToComplete = creditsRequired - creditsConverted - creditsCompleted
    if (needToComplete < 0) {
      if (returnNegative) {
        needToComplete
      } else {
        0
      }
    } else {
      needToComplete
    }
  }

  def reduceRequired(credits: Float, num: Int) {
    this.creditsRequired -= credits
    this.creditsRequired = if (this.creditsRequired < 0) 0 else this.creditsRequired
    this.numRequired -= num
    this.numRequired = if (this.numRequired < 0) 0 else this.numRequired
  }
}
