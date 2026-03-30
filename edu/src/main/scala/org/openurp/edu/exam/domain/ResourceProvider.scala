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

import org.openurp.base.model.{Project, Semester}
import org.openurp.base.resource.model.Classroom
import org.openurp.edu.exam.domain.Turn
import org.openurp.edu.exam.model.{ExamTask, InvigilationQuota}

import scala.collection.{Iterable, Seq}

/** 查询课表/考试占用、空闲教室与空闲监考名额，供排考与监考分配使用。 */
trait ResourceProvider {

  /** 统计在 `turn` 时段与 `task` 课表时间冲突的选课人数；`excludeSelf` 为真时排除本任务教学班。 */
  def statCourseOccupiedStds(task: ExamTask, turn: Turn, excludeSelf: Boolean): Int

  /** 统计在 `turn` 时段已安排其他考试、且与 `task` 考生有交集的人数。 */
  def statExamOccupiedStds(task: ExamTask, turn: Turn): Int

  /** 按 `examProblem` 中课程/考试冲突阈值判断 `task` 在 `turn` 是否不宜再排。 */
  def isStdOccupied(task: ExamTask, turn: Turn, setting: ExamSchedSetting): Boolean

  /** 在候选 `rooms` 中返回该时段无课表占用且满足教室开放规则的子集。 */
  def getFreeRooms(project: Project, turn: Turn, rooms: Iterable[Classroom]): Seq[Classroom]

  /** 同 [[getFreeRooms]]，`onlyExam` 为真时占用查询仅计考试类型活动。 */
  def getFreeRooms(project: Project, turn: Turn, rooms: Iterable[Classroom], onlyExam: Boolean): Seq[Classroom]

  /** 返回该时段仍有余量且未与已有监考时间冲突的监考名额列表。 */
  def getFreeInvigilators(project: Project, turn: Turn, semester: Semester): Seq[InvigilationQuota]
}
