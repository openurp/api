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

package org.openurp.edu.miniclazz.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.edu.model.Course
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student

import java.time.Instant

/** 专业小课选课名单
 */
class MiniClazzTaker extends LongId, Updated {

  def this(clazz: MiniClazz, std: Student) = {
    this()
    this.miniClazz = clazz
    this.course = clazz.course
    this.semester = clazz.semester
    this.std = std
    this.updatedAt = Instant.now
  }

  /** 教学任务 */
  var miniClazz: MiniClazz = _

  /** 学生 */
  var std: Student = _

  /** 课程 */
  var course: Course = _

  /** 学年学期 */
  var semester: Semester = _

  /** 成绩 */
  var scoreText: Option[String] = None
}
