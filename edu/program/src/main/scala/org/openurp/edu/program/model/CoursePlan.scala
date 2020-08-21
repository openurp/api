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
package org.openurp.edu.program.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongIdEntity
import org.beangle.data.model.pojo.TemporalOn
import org.openurp.edu.base.AuditStates
import org.openurp.edu.base.code.model.CourseType

import scala.collection.mutable

/**
 * 课程方案
 * </p> <li>1)年级</li> <li>2)培养层次</li> <li>3)学分要求</li> <li>4)课程组</li>
 *
 * @author chaostone
 * @since 2009
 */
trait CoursePlan extends LongIdEntity with Cloneable with TemporalOn {

  /**
   * 获得总学分
   */
  def credits: Float

  /**
   * 获得计划课程组
   */
  def groups: collection.Seq[CourseGroup]

  /**
   * 查询指定类型的组
   */
  def getGroup(courseType: CourseType): Option[CourseGroup]

  /**
   * 获得顶级课程组
   */
  def tops: collection.Seq[CourseGroup]

  def planCourses: collection.Seq[PlanCourse] = {
    val rs = Collections.newBuffer[PlanCourse]
    CoursePlan.addPlanCourse(this.groups, rs)
    rs
  }

  /**
   * 这个计划的学期数
   */
  def terms: Short

  def program: Program

  def auditState: AuditStates.State
}

object CoursePlan {
  /**
   * @param groups
   * @param planCourses
   */
  private def addPlanCourse(groups: collection.Seq[CourseGroup], planCourses: mutable.Buffer[PlanCourse]): Unit = {
    if (groups.nonEmpty) {
      groups foreach { g =>
        planCourses.addAll(g.planCourses)
        addPlanCourse(g.children, planCourses)
      }
    }
  }
}
