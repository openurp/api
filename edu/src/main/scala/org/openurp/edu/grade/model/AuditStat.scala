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

package org.openurp.edu.grade.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.Component
import org.openurp.base.edu.model.Course

class AuditStat extends Component {

  var requiredCredits: Float = _

  var passedCredits: Float = _

  var requiredCount: Int = _

  var passedCount: Int = _

  @transient var passedCourses = Collections.newSet[Course]

  var convertedCredits: Float = _

  def this(passedCredits: Float, totalNum: Int) = {
    this()
    this.passedCredits = passedCredits
    this.passedCount = totalNum
  }

  def addCredits(credits: Float): Unit = {
    this.passedCredits += credits
  }

  def addNum(num: Int): Unit = {
    this.passedCount += num
  }

  def passed: Boolean = {
    java.lang.Float.compare(requiredCredits, passedCredits + convertedCredits) <= 0 && requiredCount <= passedCount
  }

  def creditNeeded(returnNegative: Boolean): Float = {
    val needToComplete = requiredCredits - convertedCredits - passedCredits
    if (java.lang.Float.compare(needToComplete, 0) < 0) {
      if returnNegative then needToComplete else 0
    } else {
      needToComplete
    }
  }

  def reduceRequired(credits: Float, num: Int): Unit = {
    this.requiredCredits -= credits
    this.requiredCredits = if (this.requiredCredits < 0) 0 else this.requiredCredits
    this.requiredCount -= num
    this.requiredCount = if (this.requiredCount < 0) 0 else this.requiredCount
  }
}
