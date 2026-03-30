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

import org.openurp.base.model.Project
import org.openurp.edu.exam.model.ExamRoom

import scala.collection.Seq

/** 为一批 ExamRoom 自动写入监考安排（依实现可能只处理首席或双监考）。 */
trait InvigilatorAllocator {

  /** 在 `project`/`semester` 上下文中为 `examRooms` 分配监考并写回模型。 */
  def allocate(project: Project, examRooms: Seq[ExamRoom], setting: InvigilatorAllocateSetting): Unit
}
