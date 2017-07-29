/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.model

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.code
import org.beangle.data.model.pojo.{ TemporalOn, Updated }
import org.openurp.base.model.User
import org.openurp.edu.base.code.model.TeacherType

/**
 * 教师信息
 */
class Teacher extends LongId with Updated with TemporalOn {

  /**用户*/
  var user: User = _

  /**所在项目*/
  var project: Project = _

  /**教师类型*/
  var teacherType: TeacherType = _
}

