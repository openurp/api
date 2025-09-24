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

package org.openurp.edu.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.edu.model.Course
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.*
import org.openurp.base.std.model.Student

import java.time.Instant
import scala.collection.mutable

@beta
class MiniClazz extends LongId, ProjectBased, Updated, Cloneable, Remark {

  /** 课程序号 */
  var crn: String = _

  /** 开课院系 */
  var teachDepart: Department = _

  /** 课程 */
  var course: Course = _

  /** 学年学期 */
  var semester: Semester = _

  /** 授课教师 */
  var teacher: Option[Teacher] = None

  /** 授课学时 */
  var courseHours: Int = _

  /** 指导学时 */
  var coachHours: Int = _

  def calcHours(): Unit = {
    this.courseHours = activities.filter(_.teacher.nonEmpty).map(x => (x.endUnit - x.beginUnit + 1) * x.time.weekstate.size).sum
    this.coachHours = activities.filter(_.teacher.isEmpty).map(x => (x.endUnit - x.beginUnit + 1) * x.time.weekstate.size).sum
  }

  def scheduleHours: Int = {
    activities.map(x => (x.endUnit - x.beginUnit + 1) * x.time.weekstate.size).sum
  }


  /** 辅导老师 */
  def advisors: Set[User] = {
    val users = Collections.newSet[User]
    users.addAll(activities.flatMap(_.advisor1))
    users.addAll(activities.flatMap(_.advisor2))
    users.toSet
  }

  /** 学生名单 */
  var stds: mutable.Set[Student] = Collections.newSet[Student]

  /** 具体排课结果 */
  var activities: mutable.Set[MiniClazzActivity] = Collections.newSet[MiniClazzActivity]

  def this(crn: String, project: Project, semester: Semester, course: Course) = {
    this()
    this.crn = crn
    this.project = project
    this.course = course
    this.semester = semester
    this.updatedAt = Instant.now
  }
}
