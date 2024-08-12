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

package org.openurp.edu.grade.log

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.log
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.Course
import org.openurp.base.model.{ProjectBased, Semester, User}
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.GradeType

import java.time.Instant

/** 成绩变化历史
 */
@log
class GradeChange extends LongId, ProjectBased, Remark {

  var courseGradeId: Long = _

  var semester: Semester = _

  var course: Course = _

  var std: Student = _

  var gradeType: GradeType = _

  var scoreBefore: Option[String] = None

  var scoreAfter: Option[String] = None

  var operator: User = _

  var operateAt: Instant = _

}
