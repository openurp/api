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

package org.openurp.edu.program.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{DateRange, Remark, TemporalOn, Updated}
import org.openurp.base.edu.code.model.CourseType
import org.openurp.base.model.AuditStatus

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * 抽象课程方案
 * @author chaostone
 * @since 2009
 */
trait AbstractCoursePlan extends LongId with CoursePlan with Updated with Remark {

  /** 培养方案 */
  var program: Program = _

  /** 课程组 */
  var groups: mutable.Buffer[CourseGroup] = new ListBuffer[CourseGroup]

  /** 要求学分 */
  var credits: Float = _

  /** 起始学期 */
  var startTerm: Short = _

  /** 结束学期 */
  var endTerm: Short = _

  /** 审核状态 */
  var status: AuditStatus = AuditStatus.Draft

  def terms: Short = (endTerm - startTerm + 1).asInstanceOf[Short]

  def addGroup(group: CourseGroup): Unit = {
    groups += group
  }

  override def tops: collection.Seq[CourseGroup] = {
    val res = new ListBuffer[CourseGroup]
    for (group <- groups if group.parent == null) res += group
    res
  }

  override def getGroup(courseType: CourseType): Option[CourseGroup] = {
    groups.find(_.courseType == courseType)
  }
}
