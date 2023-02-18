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

package org.openurp.edu.exam.model

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.openurp.base.model.{Project, Semester}
import org.openurp.code.edu.model.ExamType

/** 考试通知 */
class ExamNotice extends LongId {

  var project: Project = _

  var semester: Semester = _

  var examType: ExamType = _

  var studentNotice: String = _

  var managerNotice: String = _
}
