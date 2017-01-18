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
package org.openurp.edu.lesson.model

import org.beangle.commons.model.{ LongId, Updated }
import org.openurp.base.model.Semester
import org.openurp.edu.base.code.model.{ CourseTakeType, ElectionMode }
import org.openurp.edu.base.model.{ Course, Student }

class CourseTaker extends LongId with Updated with Cloneable {

  /** 教学任务 */
  var lesson: Lesson = _

  /** 课程 */
  var course: Course = _

  /** 学期 */
  var semester: Semester = _

  /** 学生 */
  var std: Student = _

  /** 修读类别 */
  var takeType: CourseTakeType = _

  /** 选课方式 **/
  var electionMode: ElectionMode = _

  /** 备注 */
  var remark: Option[String] = None

  /** 授课对象组 */
  var limitGroup: Option[LessonLimitGroup] = None

}
