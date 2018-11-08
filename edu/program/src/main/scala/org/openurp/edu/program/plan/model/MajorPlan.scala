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
package org.openurp.edu.program.plan.model

import org.openurp.edu.base.model.{ Direction, Program, Terms }

/**
 * @author chaostone
 */
class MajorPlan extends AbstractCoursePlan {

  /**
   * 培养方案
   */
  var program: Program = _

  override def toString(): String = {
    "MajorPlanBean [program=" + program + ", startTerm=" +
      startTerm +
      ", endTerm=" +
      endTerm +
      "]"
  }
}

/**
 * 专业计划课程组.
 *   @author chaostone
 */
class MajorCourseGroup extends AbstractCourseGroup {

  /**
   * 自定义别名
   */
  var alias: Option[String] = None

  /**
   * 该组针对的专业方向
   */
  var direction: Option[Direction] = None

  def getName: String = {
    val sb = new StringBuilder()
    if (null != courseType) sb.append(courseType.name)
    if (None != alias) sb.append(" ").append(alias.get)
    sb.toString
  }

  override def toString(): String = {
    "MajorPlanCourseGroupBean [alias=" + alias + ", direction=" +
      direction +
      ", parent=" +
      parent +
      ", courseType=" +
      courseType +
      "]"
  }
}
/**
 * 专业计划课程
 *
 */
class MajorPlanCourse extends AbstractPlanCourse with ExecutePlanCourse {

  /** 建议修读学期 */
  var suggestTerms: Terms = Terms.empty

  override def toString(): String = {
    "MajorPlanCourseBean [courseGroup=" + group + ", course=" +
      course +
      ", terms=" +
      terms +
      ", compulsory=" +
      compulsory +
      ", department=" +
      department +
      ", examMode=" +
      examMode +
      "]"
  }
}
