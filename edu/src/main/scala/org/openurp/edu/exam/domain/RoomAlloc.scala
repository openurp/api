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

import org.openurp.base.resource.model.Classroom

/** 教室分配结果
 *
 * @param occupier 占用人
 * @param room     教室
 * @param capacity 总容量
 * @param alloc    分配多少容量
 */
class RoomAlloc(val occupier: RoomOccupier, val room: Classroom, val capacity: Int, var alloc: Int) {

  override def toString: String = s"${room.name} $alloc"
}
