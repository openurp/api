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
package org.openurp.edu.grade.course.service

import org.openurp.edu.lesson.model.Lesson
import org.openurp.edu.grade.code.model.GradeType
import org.openurp.edu.grade.course.model.CourseGradeState

trait CourseGradeService {

  /**
   * 按照成绩状态，重新计算成绩的<br>
   * 1、首先更改成绩的成绩记录方式<br>
   * 2、score以及是否通过和绩点等项<br>
   * 3、如果成绩状态中发布状态，则进行发布操作
   *
   * @param gradeState
   * @return
   */
  def recalculate(gradeState: CourseGradeState): Unit

  /**
   * 删除考试成绩<br>
   * 同时将该成绩和总评成绩的教师确认位置为0
   *
   * @param task
   * @param gradeType
   */
  def remove(task: Lesson, gradeType: GradeType): Unit

  /**
   * 发布或取消发布成绩
   *
   * @param lessonIdSeq
   * @param gradeType  如果为空,则发布影响总评和最终
   * @param isPublished
   */
  def publish(lessonIds: Array[java.lang.Long], gradeTypes: Array[GradeType], isPublished: Boolean): Unit

  /**
   * 查询成绩状态
   */
  def getState(lesson: Lesson): CourseGradeState
}
