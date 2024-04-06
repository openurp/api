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

package org.openurp.edu.grade.domain

import org.openurp.edu.grade.model.AuditGroupResult
import org.openurp.edu.program.model.CourseGroup

trait AuditGroupResultBuilder {

  def buildResult(context: AuditPlanContext, group: CourseGroup): AuditGroupResult
}

object DefaultAuditGroupResultBuilder extends AuditGroupResultBuilder {

  def buildResult(context: AuditPlanContext, group: CourseGroup): AuditGroupResult = {
    val result = new AuditGroupResult()
    val creditsRequired = group.credits
    result.requiredCredits = creditsRequired
    result.courseType = group.courseType
    result.name = group.name
    result.subCount = Math.min(group.subCount, group.children.size).toShort
    result.indexno = group.indexno
    result.planResult = context.result
    result
  }
}
