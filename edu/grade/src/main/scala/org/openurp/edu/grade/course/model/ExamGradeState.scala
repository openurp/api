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
package org.openurp.edu.grade.course.model

import org.beangle.data.model.annotation.config
import org.openurp.edu.grade.model.AbstractGradeState
import org.openurp.edu.base.code.model.GradeType
import org.beangle.data.model.Remark

/**
 * 考试成绩状态
 *
 * @author chaostone
 */
@config
class ExamGradeState extends AbstractGradeState  with Remark{

  /**
   * 成绩类型
   */
  var gradeType: GradeType = _

  /**
   * 总成绩状态
   */
  var gradeState: CourseGradeState = _

  /**
   * 百分比描述 <br>
   * 10% 就是 10， 20% 就是 20<br>
   */
  var percent: Option[Short] = None
}