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

package org.openurp.degree.thesis.model

import org.beangle.data.model.Component

import java.time.{Instant, LocalDate, ZoneId}
import scala.compiletime.uninitialized

/** 各阶段的时间安排
 *
 */
class StageTime extends Component {
  var stage: Stage = uninitialized
  var beginAt: Instant = uninitialized
  var endAt: Instant = uninitialized

  def this(stage: Stage, beginAt: Instant, endAt: Instant) = {
    this()
    this.stage = stage
    this.beginAt = beginAt
    this.endAt = endAt
  }

  def timeSuitable(date: Instant): Int = {
    if beginAt.isAfter(date) then -1
    else if endAt.isBefore(date) then 1
    else 0
  }

  def beginOn: LocalDate = {
    beginAt.atZone(ZoneId.systemDefault).toLocalDate
  }

  def endOn: LocalDate = {
    endAt.atZone(ZoneId.systemDefault).toLocalDate
  }
}
