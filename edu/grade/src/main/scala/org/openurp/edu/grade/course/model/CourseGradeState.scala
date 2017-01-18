/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.course.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.model.annotation.{ code, config }
import org.openurp.edu.base.code.model.ScoreMarkStyle
import org.openurp.edu.base.code.model.GradeType
import org.openurp.edu.grade.model.{ AbstractGradeState, GradeState }
import org.openurp.edu.lesson.model.Lesson
/**
 * 成绩状态表
 * 记录了对应教学任务成绩<br>
 * 1)记录方式,<br>
 * 2)各种成绩成分的百分比,<br>
 * 3)各种成绩的确认状态,<br>
 * 4)各种成绩的发布状态<br>
 */
@config
class CourseGradeState extends AbstractGradeState {

  /**
   * 教学任务
   */
  var lesson: Lesson = _

  /**
   * 可录入各成绩类型的状态设置
   */
  var examStates = Collections.newSet[ExamGradeState]

  /**
   * 可录入各成绩类型的状态设置
   */
  var gaStates = Collections.newSet[GaGradeState]

  def this(lesson: Lesson) {
    this()
    this.lesson = lesson
    this.markStyle = new ScoreMarkStyle(ScoreMarkStyle.Percent)
  }

  def updateStatus(gradeType: GradeType, status: Int) {
    val state = getState(gradeType).asInstanceOf[ExamGradeState]
    if (null == state) {
      if (gradeType.isGa) {
        val newstate = new GaGradeState
        newstate.gradeState = this
        newstate.gradeType = gradeType
        newstate.status = status
        newstate.markStyle = markStyle
        gaStates += newstate
      } else {
        val newstate = new ExamGradeState
        newstate.gradeState = this
        newstate.gradeType = gradeType
        newstate.status = status
        newstate.markStyle = markStyle
        examStates += newstate
      }
    } else {
      state.status = status
    }
  }

  def getState(gradeType: GradeType): GradeState = {
    if (gradeType.isGa)
      gaStates.find(_.gradeType.id == gradeType.id).getOrElse(null)
    else
      examStates.find(_.gradeType.id == gradeType.id).getOrElse(null)
  }

  /**
   * 是否是指定状态
   */
  def isStatus(gradeType: GradeType, status: Int): Boolean = {
    val gradeTypeState = getState(gradeType)
    if (null == gradeTypeState) false else gradeTypeState.status == status
  }

  def getPercent(gradeType: GradeType): Option[Short] = {
    examStates.find(_.gradeType.id == gradeType.id) match {
      case Some(es) => es.percent
      case None     => None
    }
  }

  def gradeType: GradeType = {
    new GradeType(GradeType.Final)
  }
}
