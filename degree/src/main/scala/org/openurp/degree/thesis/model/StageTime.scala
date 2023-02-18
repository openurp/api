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

import org.beangle.data.model.pojo.DateRange
import org.beangle.data.model.{Component, LongId}

import java.time.LocalDate

/** 各阶段的时间安排
 *
 */
class StageTime extends Component {
  var stage: Stage = _
  var beginOn: LocalDate = _
  var endOn: LocalDate = _

  def this(stage: Stage, beginOn: LocalDate, endOn: LocalDate) = {
    this()
    this.stage = stage
    this.beginOn = beginOn
    this.endOn = endOn
  }

  def timeSuitable(date: LocalDate): Int = {
    if beginOn.isAfter(date) then -1
    else if endOn.isBefore(date) then 1
    else 0
  }
}
