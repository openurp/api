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

import org.openurp.edu.program.model.CourseGroup

/**
 * 课程审核到组时，某个组是否接受按照课程类型匹配的策略
 */
trait AuditTypeMatchPolicy {

  def allowMatchType(g: CourseGroup): Boolean
}

object DefaultAuditTypeMatchPolicy extends AuditTypeMatchPolicy {

  override def allowMatchType(g: CourseGroup): Boolean = {
    if null == g then true
    else if g.courseType.optional then
      //没有子组且没有课程
      if g.children.isEmpty && g.planCourses.isEmpty then true
      else if g.allowUnplanned then
        true
      else
        val level = g.plan.level
        //没有子组且学分超过直接课程学分之和
        g.children.isEmpty && g.credits > g.planCourses.map(_.course.getCredits(level)).sum
    else false
  }
}
