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

import org.openurp.base.edu.model.Course
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.{Campus, Department}
import org.openurp.base.resource.model.{Building, Classroom}
import org.openurp.code.asset.model.ClassroomType

/** 教室分配时的「占用方」抽象：提供校区、楼栋、教室类型、候选教室集合与人数等约束。 */
trait RoomOccupier {

  def occupier: AnyRef

  def campus: Campus

  def building: Building

  def roomType: ClassroomType

  def rooms: Set[Classroom]

  def depart: Department

  def courses: Iterable[Course]

  def teachers: Iterable[Teacher]

  def stdCount: Int

  def name: String
}
