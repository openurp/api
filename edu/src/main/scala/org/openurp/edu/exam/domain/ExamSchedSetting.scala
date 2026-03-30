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

package org.openurp.edu.exam.domain

import org.beangle.commons.collection.Collections
import org.openurp.edu.exam.config.ExamAllocSetting
import org.openurp.edu.exam.domain.ExamSchedSetting.*
import org.openurp.edu.exam.model.ExamTask

import java.time.Duration
import scala.collection.mutable

/** 考场分配策略参数（来自考试配置中的策略与容量约束）。 */
class ExamSchedSetting(setting: ExamAllocSetting, val courseConflictRatio: Float, val examConflictRatio: Float) {

  val minOccupyRatio: Float = setting.minOccupyRatio

  val minCapacity: Int = setting.minCapacity

  /** 同一学生连续考试最小间隔（小时） */
  val minStdExamInterval: Duration = Duration.ofHours(setting.minStdExamInterval)

  /** 同一个考场内相同任务 */
  val sameTask: Boolean = setting.allocPolicy.sameTask

  /** 同一个考场内相同课程 */
  val sameCourse: Boolean = setting.allocPolicy.sameCourse

  /** 同一个考场内是否是同一个开课院系 */
  val sameDepart: Boolean = setting.allocPolicy.sameDepart

  /** 是否允许多个占用方共享同一教室：仅当未同时要求同院系、同课程且同任务时才为 `true`。 */
  def canShare: Boolean = !(sameDepart && sameCourse && sameTask)

  var minCourseConflictCount: Int = _

  var turnRule: TurnRule = new DefaultTurnRule()

  var conflictRule: ConflictRule = new DefaultCourseConflictRule(this)

  /** 任务是否允许排在给定时段（委托 [[turnRule]]）。 */
  def isAllowed(turn: Turn, task: ExamTask): Boolean = {
    turnRule.isAllowed(turn, task)
  }

  /** 该任务用于课程冲突判断的比例上限。 */
  def conflictRatio(task: ExamTask): Float = {
    conflictRule.conflictRatio(task)
  }

}

object ExamSchedSetting {

  trait ConflictRule {
    def conflictRatio(task: ExamTask): Float
  }

  trait TurnRule {
    def isAllowed(turn: Turn, task: ExamTask): Boolean
  }

  /** 默认场次规则：校验考试日、时段与任务时间窗、`minExamOn`，并支持按任务的 include/exclude 场次集合过滤。 */
  class DefaultTurnRule extends TurnRule {
    private val includes = Collections.newMap[ExamTask, mutable.Set[Turn]]
    private val excludes = Collections.newMap[ExamTask, mutable.Set[Turn]]

    override def isAllowed(turn: Turn, task: ExamTask): Boolean = {
      if task.examOn != null && turn.examOn != task.examOn then return false
      if task.beginAt != null && task.endAt != null && !task.isEmptyTime then {
        if turn.beginAt.value >= task.endAt.value || task.beginAt.value >= turn.endAt.value then return false
      }
      if task.minExamOn.exists(d => turn.examOn.isBefore(d)) then return false
      excludes.get(task) match {
        case Some(s) => return !s.contains(turn)
        case None =>
      }
      includes.get(task) match {
        case Some(s) => s.contains(turn)
        case None => true
      }
    }

    def include(task: ExamTask, turn: Turn): Unit = {
      val s = includes.getOrElseUpdate(task, mutable.HashSet[Turn]())
      s += turn
    }

    def exclude(task: ExamTask, turn: Turn): Unit = {
      val s = excludes.getOrElseUpdate(task, mutable.HashSet[Turn]())
      s += turn
    }
  }

  /** 课程冲突比例：优先任务上配置，其次本规则中按任务覆盖，最后回落到 [[ExamSchedSetting.courseConflictRatio]]。 */
  class DefaultCourseConflictRule(val setting: ExamSchedSetting) extends ConflictRule {
    private val ratios = Collections.newMap[ExamTask, Float]

    def addRatio(task: ExamTask, ratio: Float): Unit = {
      ratios.put(task, Float.box(ratio))
    }

    override def conflictRatio(task: ExamTask): Float = {
      var ratio = task.maxCourseConflictRatio
      if ratio.isEmpty then ratio = ratios.get(task)
      ratio.getOrElse(setting.courseConflictRatio)
    }
  }

}
