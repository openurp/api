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

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo._
import org.openurp.code.edu.model.Language
import org.openurp.base.edu.EduLevelBased
import org.openurp.base.edu.code.model.{CourseAbilityRate, CourseType}
import org.openurp.base.edu.model.{Course, Terms}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * 公共共享计划
 *
 * @author chaostone
 */
class SharePlan extends LongId with EduLevelBased with Named with Updated with TemporalOn with Remark with Cloneable {

  /** 起始年级 */
  var fromGrade: String = _

  /** 截止年级(包含) */
  var toGrade: String = _

  /** 课程组 */
  var groups: mutable.Buffer[ShareCourseGroup] = new ListBuffer[ShareCourseGroup]

  def addGroup(group: ShareCourseGroup): Unit = {
    groups += group
  }

  def tops: collection.Seq[ShareCourseGroup] = {
    val res = new ListBuffer[ShareCourseGroup]
    for (group <- groups if group.parent == null) res += group
    res
  }

  def getGroup(courseType: CourseType): Option[ShareCourseGroup] = {
    groups.find(_.courseType == courseType)
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

}

/**
 * 公共共享课程组课程
 *
 * @author chaostone
 */
class SharePlanCourse extends LongId with Executable {
  /**
   * 课程组
   */
  var group: ShareCourseGroup = _

  /**
   * 课程
   */
  var course: Course = _

  /**
   * 学期
   */
  var terms: Terms = _

}