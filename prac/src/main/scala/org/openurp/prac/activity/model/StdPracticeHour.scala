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

package org.openurp.prac.activity.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.std.model.Student
import org.openurp.code.prac.model.StdPracticeCategory

/** 学生实践学时
 */
class StdPracticeHour extends LongId, Updated {

  var std: Student = _
  /** 实践课程大类 */
  var category: StdPracticeCategory = _
  /** 要求的实践学时 */
  var requiredHours: Int = _
  /** 完成的实践学时 */
  var hours: Int = _
  /** 已转为课程成绩 */
  var courseGradeId: Option[Long] = None
}
