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
import org.openurp.base.model.Campus
import org.openurp.edu.exam.domain.ExamSchedSolution.*
import org.openurp.edu.exam.model.ExamTask

import java.time.LocalDate

class ExamSchedSolution(val allocations: collection.Seq[Allocation],
                        val fails: collection.Seq[ExamTask],
                        val turnAreas: collection.Map[Turn, Iterable[RoomCapacity]]) {

  /** 与给定日/起止时刻在「忽略结束时刻」意义下相同的场次上的分配，按占用方名称排序。 */
  def getAllocations(examOn: LocalDate, beginAt: HourMinute, endAt: HourMinute): collection.Seq[Allocation] = {
    val testTurn = Turn(examOn, beginAt, endAt)
    allocations
      .filter(_.turn.equalsIgnoreEndAt(testTurn))
      .sorted(using allocationByOccupierName)
  }

  /** 汇总该时段各校区教室容量、空闲与实考占用等统计（用于报表）。 */
  def getAllocStats(date: LocalDate, beginAt: HourMinute, endAt: HourMinute): AllocStats = {
    val turn = Turn(date, beginAt, endAt)
    val stats = Collections.newMap[Campus, AllocStat]
    turnAreas.get(turn) foreach { roomCapacities =>
      for (rc <- roomCapacities) {
        val stat = stats.getOrElseUpdate(rc.room.campus, new AllocStat(rc.room.campus))
        stat.addRoomCapacity(rc)
      }
    }
    for (al <- allocations if al.turn.equalsIgnoreEndAt(turn)) {
      val occ = al.occupier
      val stat = stats.getOrElseUpdate(occ.campus, new AllocStat(occ.campus))
      stat.examClazzCount += 1
      for (ra <- al.rooms) {
        stat.addRoomAlloc(ra)
      }
    }
    new ExamSchedSolution.AllocStats(stats.values)
  }
}

object ExamSchedSolution {

  class AllocStats(stats: Iterable[AllocStat]) {

    def roomCapacity: Int = stats.map(_.roomCapacity).sum

    def roomCount: Int = stats.map(_.roomCount).sum

    def freeCapacity: Int = stats.map(_.freeCapacity).sum

    def freeCount: Int = stats.map(_.freeCount).sum

    def examStdCount: Int = stats.map(_.examStdCount).sum

    def examClazzCount: Int = stats.map(_.examClazzCount).sum

    def examRoomCount: Int = stats.map(_.examRoomCount).sum
  }

  class AllocStat(val campus: Campus) {

    private val roomIds = Collections.newSet[Long]

    var roomCapacity: Int = 0

    var roomCount: Int = 0

    var freeCapacity: Int = 0

    var freeCount: Int = 0

    var examStdCount: Int = 0

    var examClazzCount: Int = 0

    def addRoomAlloc(ra: RoomAlloc): Unit = {
      examStdCount += ra.alloc
      roomIds += ra.room.id
    }

    def addRoomCapacity(rc: RoomCapacity): Unit = {
      roomCapacity += rc.capacity
      roomCount += 1
      if rc.isFullFree then {
        freeCount += 1
        freeCapacity += rc.free
      }
    }

    def examRoomCount: Int = roomIds.size
  }

  /** 与 `MultiPropertyComparator("occupier.name")` 等价 */
  private val allocationByOccupierName: Ordering[Allocation] =
    Ordering.by(a => Option(a.occupier.name).getOrElse(""))
}
