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
import org.beangle.commons.lang.time.HourMinute
import org.openurp.base.resource.model.Classroom
import org.openurp.edu.exam.domain.ExamSchedProblem.*
import org.openurp.edu.exam.model.{ExamGroup, ExamTask}

import java.text.SimpleDateFormat
import scala.collection.{Seq, mutable}

object ExamSchedProblem {

  private def collectRooms(examGroup: ExamGroup, examTasks: Iterable[ExamTask]): Set[Classroom] = {
    val rooms = Collections.newSet[Classroom]
    rooms ++= examGroup.rooms
    val groupRooms = examGroup.rooms.toSet
    for (task <- examTasks) {
      task.roomGroup match {
        case None => rooms ++= task.rooms
        case Some(rg) => rooms ++= rg.rooms
      }
    }
    rooms.toSet
  }

  private def collectTaskRooms(examGroup: ExamGroup, examTasks: Iterable[ExamTask]): Map[ExamTask, Set[Classroom]] = {
    val taskRooms = Collections.newMap[ExamTask, Set[Classroom]]
    val groupRooms = examGroup.rooms.toSet
    for (task <- examTasks) {
      task.roomGroup match {
        case None =>
          if task.rooms.isEmpty then taskRooms.put(task, groupRooms)
          else {
            taskRooms.put(task, task.rooms.toSet)
          }
        case Some(rg) =>
          taskRooms.put(task, rg.rooms.toSet)
      }
    }
    taskRooms.toMap
  }

  /** 将考试组中的场次定义转为排考用的 [[Turn]] 列表。 */
  def buildTurns(group: ExamGroup): List[Turn] = {
    val buf = Collections.newBuffer[Turn]
    for (dt <- group.turns) {
      buf += new Turn(dt.examOn, dt.beginAt, dt.endAt)
    }
    buf.toList
  }

  /** 由开始时刻与考试时长（分钟）推算结束时刻（同日内的 `HourMinute` 编码）。 */
  def calcEndAt(beginAt: HourMinute, duration: Int): HourMinute = {
    var hour = beginAt.value / 100
    var minute = beginAt.value % 100
    minute += duration
    if minute >= 60 then {
      hour += minute / 60
      minute %= 60
    }
    new HourMinute((hour * 100 + minute).toShort)
  }
}

/** 一次考试组的排考输入：任务与可用教室、场次、冲突参数及可插拔规则。
 *
 * 构造时会根据任务的教室组/自选教室填充 [[taskRooms]] 与全局 [[rooms]]。
 */
class ExamSchedProblem(val examGroup: ExamGroup, val examTasks: Iterable[ExamTask], val setting: ExamSchedSetting) {

  val id: String = s"${examGroup.id}_${new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(examGroup.updatedAt)}"
  val turns: List[Turn] = buildTurns(examGroup)
  val taskRooms: Map[ExamTask, Set[Classroom]] = collectTaskRooms(examGroup, examTasks)
  val rooms: Set[Classroom] = collectRooms(examGroup, examTasks)
  val occupiers: mutable.Map[ExamTask, mutable.Buffer[RoomOccupier]] = Collections.newMap

  def this(group: ExamGroup, setting: ExamSchedSetting) = {
    this(group, group.tasks, setting)
  }

}
