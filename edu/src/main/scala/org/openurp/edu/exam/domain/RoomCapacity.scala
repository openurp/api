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
import org.openurp.base.resource.model.Classroom

class RoomCapacity(val room: Classroom, initialFree: Int) {

  private val occupierSet = Collections.newSet[RoomOccupier]
  private var remaind: Int = initialFree
  val capacity: Int = initialFree

  def isFullFree: Boolean = free == capacity

  def occupiers: Iterable[RoomOccupier] = occupierSet

  override def clone(): AnyRef = {
    new RoomCapacity(room, free)
  }

  def occupy(occupier: RoomOccupier, cnt: Int): Unit = {
    require(free >= cnt)
    occupierSet.add(occupier)
    remaind -= cnt
  }

  def release(occupier: RoomOccupier, cnt: Int): Unit = {
    require(remaind + cnt <= capacity)
    if (occupierSet.remove(occupier)) {
      remaind += cnt
    }
  }

  def free: Int = remaind

  def isSameDepart(occupier: RoomOccupier): Boolean = {
    occupiers.forall(_.depart == occupier.depart)
  }

  def isSameCourse(occupier: RoomOccupier): Boolean = {
    occupiers.forall(_.courses == occupier.courses)
  }

  def isSameTeacher(occupier: RoomOccupier): Boolean = {
    occupiers.forall(_.teachers == occupier.teachers)
  }

  override def toString: String = s"${room.name} $capacity $free"
}
