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
import org.beangle.data.model.pojo.Updated
import org.openurp.base.edu.model.Course
import org.openurp.code.edu.model.{CourseType, EducationLevel, TeachingNature}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * 抽象课程方案
 *
 * @author chaostone
 * @since 2009
 */
trait AbstractCoursePlan extends LongId, CoursePlan, Updated {
  /** 培养方案 */
  var program: Program = _
  /** 课程组 */
  var groups: mutable.Buffer[CourseGroup] = new ListBuffer[CourseGroup]
  /** 要求学分 */
  var credits: Float = _
  /** 课时 */
  var creditHours: Int = _
  /** 课时比例 */
  var hourRatios: String = _

  override def level: EducationLevel = program.level

  override def terms: Short = program.terms

  def addGroup(group: CourseGroup): Unit = {
    groups += group
  }

  override def topGroups: collection.Seq[CourseGroup] = {
    val res = new ListBuffer[CourseGroup]
    for (group <- groups if group.parent.isEmpty) res += group
    res.sortBy(_.indexno)
  }

  override def getGroup(courseType: CourseType): Seq[CourseGroup] = {
    groups.filter(_.courseType == courseType).toSeq
  }

  override def getGroup(name: String): Option[CourseGroup] = {
    groups.find(_.name == name)
  }

  override def getGroup(course: Course): Option[CourseGroup] = {
    groups.find(_.planCourses.exists(_.course == course))
  }

  override def addGroup(newGroup: CourseGroup, parent: Option[CourseGroup]): Unit = {
    val g = newGroup.asInstanceOf[AbstractCourseGroup]
    groups.addOne(g)
    g.plan = this
    g.parent = parent
    parent foreach { p =>
      p.asInstanceOf[AbstractCourseGroup].children.addOne(newGroup)
    }
  }

  def getHours(natures: collection.Seq[TeachingNature]): Map[TeachingNature, Int] = {
    CreditHours.toHours(creditHours, hourRatios, natures, false)
  }
}
