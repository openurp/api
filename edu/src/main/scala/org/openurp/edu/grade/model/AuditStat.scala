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
import org.openurp.code.edu.model.EducationLevel

class AuditStat extends Component {

  /** 要求学分 */
  var requiredCredits: Float = _

  /** 通过学分 */
  var passedCredits: Float = _

  /** 要求门数 */
  var requiredCount: Short = _

  /** 完成门数 */
  var passedCount: Short = _

  /** 正在修读学分 */
  var takingCredits: Float = _

  /** 正在修读的门数 */
  var takingCount: Short = _

  @transient var passedCourses = Collections.newSet[Course]
  @transient var takingCourses = Collections.newSet[Course]

  /** 转换学分 */
  var convertedCredits: Float = _

  def this(passedCredits: Float, totalNum: Int) = {
    this()
    this.passedCredits = passedCredits
    this.passedCount = totalNum.toShort
  }

  def addPassed(course: Course, level: EducationLevel): Unit = {
    if (!this.passedCourses.contains(course)) {
      this.passedCourses.add(course)
      val credits = course.getCredits(level)
      this.passedCredits += credits
      this.passedCount = (this.passedCount + 1).toShort
    }
  }

  def addTaking(course: Course, level: EducationLevel): Unit = {
    if (!this.passedCourses.contains(course) && !this.takingCourses.contains(course)) {
      this.takingCourses.add(course)
      val credits = course.getCredits(level)
      this.takingCredits += credits
      this.takingCount = (this.takingCount + 1).toShort
    }
  }

  def passed: Boolean = {
    java.lang.Float.compare(requiredCredits, passedCredits + convertedCredits) <= 0 && requiredCount <= passedCount
  }

  def predicted: Boolean = {
    if passed then true
    else
      java.lang.Float.compare(requiredCredits, passedCredits + convertedCredits + takingCredits) <= 0
        && requiredCount <= (passedCount + takingCount)
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
    this.requiredCount = (this.requiredCount - num).toShort
    this.requiredCount = if (this.requiredCount < 0) 0 else this.requiredCount
  }
}
