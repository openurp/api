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
package org.openurp.eams.grade.domain

import org.openurp.edu.grade.code.model.GradeType
import org.openurp.edu.lesson.model.CourseTake
import org.openurp.edu.lesson.model.ExamTake

/**
 * 成绩给分策略<br>
 * 对于给定的学生选课记录是否在某种考试情况下，给予某一种成绩类型的成绩
 * 例如免修学生不给平时成绩等
 *
 * @author chaostone
 */
trait GradeTypePolicy {

  /**
   * 是否给予学生某种成绩
   */
  def shouldHaving(take: CourseTake, gradeType: GradeType, examtake: ExamTake): Boolean
}
