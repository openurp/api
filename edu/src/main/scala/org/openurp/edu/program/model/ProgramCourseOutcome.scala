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

package org.openurp.edu.program.model

import org.beangle.commons.lang.Strings
import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.LongId
import org.openurp.base.edu.model.Course

/** 计划课程对应的毕业要求
 */
@beta
class ProgramCourseOutcome extends LongId {

  var doc: ProgramDoc = _
  /** 序号(从1开始) */
  var idx: Int = _

  var groupName: String = _

  var courseName: String = _

  var course: Option[Course] = None

  var outcomes: String = _

  def support(outcome: ProgramOutcome): Boolean = {
    Strings.split(outcomes).toSet.contains(outcome.code)
  }
}
