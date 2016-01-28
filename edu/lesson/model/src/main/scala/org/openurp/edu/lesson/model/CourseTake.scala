/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.lesson.model

import org.beangle.data.model.TemporalOn
import org.beangle.data.model.LongId
import org.openurp.base.model.Semester
import org.openurp.edu.base.model.{ Course, Student }
import org.openurp.edu.lesson.code.model.{ CourseTakeType, ElectionMode }
import org.beangle.data.model.Updated

class CourseTake extends LongId with Updated with Cloneable {

  /** 教学任务 */
  var lesson: Lesson = _

  var course: Course = _

  var semester: Semester = _

  /** 学生 */
  var std: Student = _

  /** 修读类别 */
  var takeType: CourseTakeType = _

  /** 选课方式 **/
  var electionMode: ElectionMode = _

  /** 选课轮次 */
  var turn: Integer = _

  /** 备注 */
  var remark: String = _

  /** 授课对象组 */
  var limitGroup: LessonLimitGroup = _

}
