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

import org.beangle.data.model.LongId
import org.openurp.code.edu.model.GradeType

/** 教学大纲-成绩评分比例
 */
class SyllabusAssessPercent extends LongId {

  var syllabus: Syllabus = _

  /** 成绩类型 */
  var gradeType: GradeType = _

  /** 小项 */
  var component: Option[String] = None

  /** 百分比 */
  var scorePercent: Int = _

  /** 对应课程目标 */
  var objectives: Option[String] = None
}
