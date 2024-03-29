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

package org.openurp.edu.grade.model

import org.beangle.commons.collection.Collections
import org.openurp.base.model.User
import org.openurp.code.edu.model.{GradeType, GradingMode}
import org.openurp.edu.clazz.model.Clazz

import java.time.Instant

/**
 * 成绩状态表
 * 记录了对应教学任务成绩<br>
 * 1)记录方式,<br>
 * 2)各种成绩成分的百分比,<br>
 * 3)各种成绩的确认状态,<br>
 * 4)各种成绩的发布状态<br>
 */
class CourseGradeState extends AbstractGradeState {

  /**
   * 教学任务
   */
  var clazz: Clazz = _

  /**
   * 可录入各成绩类型的状态设置
   */
  var examStates = Collections.newSet[ExamGradeState]

  /**
   * 可录入各成绩类型的状态设置
   */
  var gaStates = Collections.newSet[GaGradeState]

  /** 保留小数位 */
  var scorePrecision: Int = _

  /** 其他录入员 */
  var inputer: Option[User] = None

  def this(clazz: Clazz) = {
    this()
    this.clazz = clazz
    this.gradingMode = new GradingMode(GradingMode.Percent)
  }

  def updateStatus(gradeType: GradeType, status: Int): Unit = {
    val state = getState(gradeType)
    if (null == state) {
      if (gradeType.isGa) {
        val newstate = new GaGradeState
        newstate.gradeState = this
        newstate.gradeType = gradeType
        newstate.status = status
        newstate.gradingMode = gradingMode
        gaStates += newstate
      } else {
        val newstate = new ExamGradeState
        newstate.gradeState = this
        newstate.gradeType = gradeType
        newstate.status = status
        newstate.gradingMode = gradingMode
        examStates += newstate
      }
    } else {
      state.status = status
    }
  }

  def getState(gradeType: GradeType): GradeState = {
    if (gradeType.isGa)
      gaStates.find(_.gradeType.id == gradeType.id).orNull
    else
      examStates.find(_.gradeType.id == gradeType.id).orNull
  }

  def getStatus(gradeType: GradeType): Int = {
    if (gradeType.isGa)
      gaStates.find(_.gradeType.id == gradeType.id).map(_.status).getOrElse(0)
    else
      examStates.find(_.gradeType.id == gradeType.id).map(_.status).getOrElse(0)
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
      case Some(es) => es.scorePercent
      case None => None
    }
  }

  def updateStatus(gradeTypes: Iterable[GradeType], status: Int, updatedAt: Instant, operator: String): Unit = {
    gradeTypes foreach { gradeType =>
      if (gradeType.id == GradeType.EndGa) this.status = status
      val gs = this.getState(gradeType).asInstanceOf[AbstractGradeState]
      gs.operator = operator
      gs.status = status
      gs.updatedAt = updatedAt
    }
    this.updatedAt = updatedAt
    this.operator = operator
  }

  def gradeType: GradeType = {
    new GradeType(GradeType.Final)
  }
}
