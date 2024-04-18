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

package org.openurp.edu.course.model

import org.beangle.commons.lang.Strings
import org.beangle.data.model.LongId
import org.openurp.code.edu.model.GraduateObjective

/** 教学大纲-毕业要求
 *
 */
class SyllabusOutcome extends LongId {
  var syllabus: Syllabus = _

  /** 毕业要求 */
  var objective: GraduateObjective = _

  /** 内容 */
  var contents: String = _

  /** 对应课程目标 */
  var courseObjectives: String = _

  def this(syllabus: Syllabus, objective: GraduateObjective, contents: String, courseObjectives: String) = {
    this()
    this.syllabus = syllabus
    this.objective = objective
    this.contents = contents
    this.courseObjectives = courseObjectives
  }

  def support(syllabusObjective: SyllabusObjective): Boolean = {
    Strings.split(courseObjectives).toSet.contains(syllabusObjective.code)
  }

}
