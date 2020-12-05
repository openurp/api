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

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.User
import org.openurp.base.edu.model.Course


/** 课程简介
 *
 */
class CourseProfile extends LongId with Updated {

  /** 课程 */
  var course: Course = _

  /** 简介 */
  var description: String = _

  /** 英文简介 */
  var enDescription: Option[String] = None

  /** 先修课程 */
  var prerequisites: Option[String] = None

  /** 适用专业 */
  var majors: Option[String] = None

  /** 教材和参考书目 */
  var textbooks: Option[String] = None

  /** 辅助资料 */
  var materials: Option[String] = None

  /** 课程网站地址 */
  var website: Option[String] = None

  /** 更新人 */
  var updatedBy: Option[User] = None
}
