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

import org.beangle.commons.lang.time.HourMinute

import java.time.{Duration, LocalDate, LocalDateTime, LocalTime}

/** 一次考试排期：日期与起止时刻（`HourMinute` 编码），用作场次键与团索引。 */
case class Turn(examOn: LocalDate, beginAt: HourMinute, endAt: HourMinute) extends Ordered[Turn] {

  def equalsIgnoreEndAt(o: Turn): Boolean = {
    examOn == o.examOn && beginAt == o.beginAt
  }

  override def toString: String = s"$examOn $beginAt~$endAt"

  override def compare(o: Turn): Int = {
    val rs = this.examOn.compareTo(o.examOn)
    if rs == 0 then this.beginAt.compareTo(o.beginAt) else rs
  }

  /** 两个场次开始时刻之间相差的整小时数（绝对值，用于相邻场次判定）。 */
  def distance(o: Turn): Duration = {
    val lt = Turn.beginLocalDateTime(this)
    val ot = Turn.beginLocalDateTime(o)
    Duration.between(lt, ot).abs()
  }
}

object Turn {

  private def beginLocalDateTime(t: Turn): LocalDateTime = {
    LocalDateTime.of(t.examOn, LocalTime.of(t.beginAt.value / 100, t.beginAt.value % 100))
  }
}
