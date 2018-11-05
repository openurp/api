/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright (c) 2005, The OpenURP Software.
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
package org.openurp.edu.grade.audit.domain

import org.openurp.edu.base.code.model.CourseType
import org.beangle.commons.collection.Collections

import AuditSetting._

object AuditSetting {
  val OutofPlanElectiveType = new CourseType(-1, "-1", "计划外", "计划外")

  def empty = new AuditSetting(Set.empty, Set.empty, OutofPlanElectiveType)
}

class AuditSetting(
  val disauditCourseTypes:    Set[CourseType],
  val convertableCourseTypes: Set[CourseType],
  convertTarcourseType:       CourseType) {

  val convertTarget =
    {
      if (null == convertTarcourseType) {
        AuditSetting.OutofPlanElectiveType
      } else {
        convertTarcourseType
      }
    }

  var auditTerms: Array[Int] = _

  var partial: Boolean = _

  def isConvertable(courseType: CourseType): Boolean = {
    convertableCourseTypes.contains(courseType)
  }

  def isDisaudit(courseType: CourseType): Boolean = {
    if (Collections.isNotEmpty(disauditCourseTypes)) {
      return disauditCourseTypes.contains(courseType)
    }
    false
  }

  def isConvertableCourseType(courseType: CourseType): Boolean = {
    if (Collections.isNotEmpty(convertableCourseTypes)) {
      return convertableCourseTypes.contains(courseType)
    }
    false
  }

  def setAuditTerms(auditTerms: Array[Int]) {
    this.auditTerms = auditTerms
    this.partial = if (auditTerms == null || auditTerms.length == 0) false else true
  }
}
