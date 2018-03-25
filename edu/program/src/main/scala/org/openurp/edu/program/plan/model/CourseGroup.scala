/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.program.plan.model

import org.beangle.data.model.LongIdEntity
import org.beangle.data.model.pojo.Named
import org.openurp.edu.base.code.model.CourseType

/**
 * 课程设置中的课程组. </p> <li>1)对应计划</li> <li>2)课程类型</li> <li>3)要求学分</li> <li>4)是否必修课</li>
 * <li>5)父组</li> <li>6)子组集合</li> <li>7)组内所有的课程</li> <li>8)备注</li>
 *
 * @author chaostone
 */
trait CourseGroup extends LongIdEntity with Ordered[CourseGroup] with Named {

  /**
   * 获得课程方案
   */
  def plan: CoursePlan

  /**
   * 获得上级组
   */
  def parent: Option[CourseGroup]

  /**
   * 获得子节点集合.
   */
  def children: Seq[CourseGroup]

  /**
   * 要求完成的课程组数量
   * (-1表示全部完成)
   */
  def groupNum: Short

  /**
   * 获得课程类别.
   */
  def courseType: CourseType

  /**
   * 要求组内要求总学分
   */
  def credits: Float

  /**
   * 获得组内要求门数
   */
  def courseNum: Short

  def planCourses: Seq[PlanCourse]

  /**
   * 判断是否必修组<br>
   * 如果组内没有课程和子组，那么就是选修<br>
   * 如果组内有课程没子组，那么所有课程都是必修才是必修，否则就是选修<br>
   * 如果组内无课程有子组，那么组关系必须是AND才是必修，否则就是选修<br>
   * 如果组内有课程有子组，那么所有课程都是必修且关系AND才是必修，否则就是选修
   */
  def compulsory: Boolean

  /**
   * 获得备注.
   */
  def remark: Option[String]

  /**
   * 获得每学期学分
   */
  def termCredits: String

  def indexno: String
}
