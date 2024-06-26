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

trait AuditPlanListener {

  /**
   * 开始审核计划
   *
   * @return false 表示不能继续审核
   */
  def start(context: AuditPlanContext): Unit = {}

  /**
   * 开始审核课程组
   *
   * @return false 表示不能继续审核
   */
  def startGroup(context: AuditPlanContext, courseGroup: CourseGroup, groupResult: AuditGroupResult): Boolean = true

  /**
   * 结束审核计划
   */
  def end(context: AuditPlanContext): Unit = {}
}
