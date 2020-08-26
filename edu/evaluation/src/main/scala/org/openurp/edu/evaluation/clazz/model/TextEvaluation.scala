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
package org.openurp.edu.evaluation.clazz.model

import org.openurp.edu.base.model.Student
import org.beangle.data.model.LongId
import org.openurp.edu.clazz.model.Clazz
import org.beangle.commons.collection.Collections
import org.openurp.edu.base.model.Teacher
import java.time.Instant

/**
 * 开放式文字评教
 */
class TextEvaluation extends LongId {
  /** 教学任务 */
  var clazz: Clazz = _
  /** 教师 */
  var teacher: Teacher = _
  /** 学生 */
  var student: Student = _
  /**评教内容*/
  var contents: String = _
  /** 评教时间 */
  var evaluateAt: Instant = _
  /** 是否已审核 */
  var audited: Boolean = false
  /** 是否教师评教 */
  var evaluateByTeacher: Boolean = _
  /**教师回复*/
  var teacherRemessages = Collections.newBuffer[TeacherRemessage]

}
