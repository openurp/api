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
import org.openurp.edu.base.model.Semester
import org.openurp.edu.base.model.Student
import org.openurp.code.edu.model.ExamStatus
import org.openurp.code.edu.model.ExamType

class ExamTaker extends LongId with Cloneable {

  /**学年学期*/
  var semester: Semester = _

  /** 教学任务 */
  var clazz: Clazz = _

  /** 学生 */
  var std: Student = _

  /** 考试类型 */
  var examType: ExamType = _

  /** 考试情况 */
  var examStatus: ExamStatus = _

  /** 缓考申请原因/记录处分 */
  var remark: Option[String] = None

}
