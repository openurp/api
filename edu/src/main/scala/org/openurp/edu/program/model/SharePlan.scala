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

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.{Numbers, Strings}
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.*
import org.openurp.base.edu.model.{Course, Terms}
import org.openurp.base.model.EduLevelBased
import org.openurp.base.std.model.Grade
import org.openurp.code.edu.model.{CourseAbilityRate, CourseType}
import org.openurp.code.person.model.Language

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * 公共共享计划
 *
 * @author chaostone
 */
class SharePlan extends LongId with EduLevelBased with Named with Updated with TemporalOn with Remark with Cloneable {

  /** 起始年级 */
  var fromGrade: Grade = _

  /** 截止年级(包含) */
  var toGrade: Grade = _

  /** 课程组 */
  var groups: mutable.Buffer[ShareCourseGroup] = new ListBuffer[ShareCourseGroup]

  def addGroup(group: ShareCourseGroup): Unit = {
    groups += group
  }

  def addGroup(g: ShareCourseGroup, parent: Option[ShareCourseGroup]): Unit = {
    groups.addOne(g)
    g.plan = this
    g.parent = parent
    parent foreach { p =>
      p.children.addOne(g)
    }
  }

  def topGroups: collection.Seq[ShareCourseGroup] = {
    val res = new ListBuffer[ShareCourseGroup]
    for (group <- groups if group.parent == null) res += group
    res
  }

  def getGroup(courseType: CourseType): Option[ShareCourseGroup] = {
    groups.find(_.courseType == courseType)
  }

  def course2Types: Map[Course, CourseType] = {
    val data = Collections.newMap[Course, CourseType]
    for (group <- groups; pc <- group.planCourses) {
      data.put(pc.course, group.courseType)
    }
    data.toMap
  }

  def planCourses: collection.Seq[SharePlanCourse] = {
    val rs = Collections.newBuffer[SharePlanCourse]
    addPlanCourse(this.groups, rs)
    rs
  }

  /**
   * @param groups
   * @param planCourses
   */
  private def addPlanCourse(groups: collection.Seq[ShareCourseGroup], planCourses: mutable.Buffer[SharePlanCourse]): Unit = {
    if (groups.nonEmpty) {
      groups foreach { g =>
        planCourses.addAll(g.planCourses)
        addPlanCourse(g.children, planCourses)
      }
    }
  }
}

/**
 * 公共共享课程组(默认实现)
 */
class ShareCourseGroup extends LongId with Hierarchical[ShareCourseGroup] {

  /**
   * 对应外语语种
   */
  var language: Option[Language] = None

  /**
   * *
   * 要求语言等级
   */
  var courseAbilityRate: Option[CourseAbilityRate] = None

  /**
   * 计划
   */
  var plan: SharePlan = _

  /**
   * 计划课程列表
   */
  var planCourses: mutable.Buffer[SharePlanCourse] = new ListBuffer[SharePlanCourse]

  /**
   * 课程类别
   */
  var courseType: CourseType = _

  def index: Int = {
    var index = Strings.substringAfterLast(indexno, ".")
    if (Strings.isEmpty(index)) index = indexno
    var idx = Numbers.toInt(index)
    if (idx <= 0) idx = 1
    idx
  }
}

/**
 * 公共共享课程组课程
 *
 * @author chaostone
 */
class SharePlanCourse extends LongId {
  /** 课程组 */
  var group: ShareCourseGroup = _

  /** 课程 */
  var course: Course = _

  /** 是否必修 */
  var compulsory: Boolean = _

  /** 学期 */
  var terms: Terms = _

}
