/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.edu.grade.course.model

import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.Remark
import org.openurp.code.edu.model.GradeType
import org.openurp.edu.grade.model.AbstractGradeState

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
  var scorePercent: Option[Short] = None
}
