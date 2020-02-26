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
package org.openurp.edu.course.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.{Department, User}
import org.openurp.edu.base.model.{Course, Semester}

import scala.collection.mutable

/** 课程资料
 *
 */
class CourseBlog extends LongId with Updated {

  /** 学年学期 */
  var semester: Semester = _

  /** 课程 */
  var course: Course = _

  /** 授课教师 */
  var teachers: mutable.Set[User] = Collections.newSet[User]

  /** 简介 */
  var description: String = _

  /** 英文简介 */
  var enDescription: Option[String] = None

  /** 开课院系 */
  var department: Department = _

  /** 作者 */
  var author: User = _

  /** 教材和辅助资料 */
  var materials: Option[String] = None

  /** 课程网站 */
  var website: Option[String] = None

  /** 状态 */
  var status: BlogStatus.Status = BlogStatus.Draft
}