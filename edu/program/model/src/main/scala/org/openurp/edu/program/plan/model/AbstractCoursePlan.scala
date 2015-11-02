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
package org.openurp.edu.program.plan.model

import scala.collection.mutable.{ Buffer, ListBuffer }

import org.beangle.commons.lang.Strings
import org.beangle.data.model.{ TemporalOn, Updated }
import org.openurp.edu.base.States
import org.openurp.edu.base.code.model.CourseType
/**
 * 抽象课程方案
 *
 * @author chaostone
 * @since 2009
 */
trait AbstractCoursePlan extends CoursePlan with Updated with TemporalOn {

  /**
   * 课程组
   */
  var groups: Buffer[CourseGroup] = new ListBuffer[CourseGroup]

  /**
   * 要求学分
   */
  var credits: Float = _

  /**
   * 起始学期
   */
  var startTerm: Short = _

  /**
   * 结束学期
   */
  var endTerm: Short = _

  /**
   * 审核状态
   */
  var state: States.State = States.Draft

  var remark: String = _

  def terms = (endTerm - startTerm + 1).asInstanceOf[Short]

  def addGroup(group: CourseGroup) {
    groups += group
  }

  override def tops: Seq[CourseGroup] = {
    val res = new ListBuffer[CourseGroup]
    for (group <- groups if group.parent == null) res += group
    res
  }

  override def group(courseType: CourseType): CourseGroup = {
    groups.find(_.courseType == courseType).orNull
  }
}
