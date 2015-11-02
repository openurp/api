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

import org.beangle.commons.collection.Collections
import org.beangle.data.model.TemporalOn
import org.beangle.data.model.LongId
import org.openurp.edu.base.model.StudentScope
import org.openurp.edu.base.code.model.CourseType

class PlanAuditStandard extends LongId with TemporalOn {

  var name: String = _

  var studentScope: StudentScope = new StudentScope

  var disauditCourseTypes = new collection.mutable.HashSet[CourseType]

  var convertableCourseTypes = new collection.mutable.HashSet[CourseType]

  var convertTarCourseType: CourseType = _

  var remark: String = _

  def isConvertableCourseType(courseType: CourseType): Boolean = {
    if (!Collections.isEmpty(convertableCourseTypes)) {
      return convertableCourseTypes.contains(courseType)
    }
    false
  }

  def isConvertable(courseType: CourseType): Boolean = {
    convertableCourseTypes.contains(courseType)
  }

  def isDisaudit(courseType: CourseType): Boolean = {
    if (!Collections.isEmpty(disauditCourseTypes)) {
      return disauditCourseTypes.contains(courseType)
    }
    false
  }
}
