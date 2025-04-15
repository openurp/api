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

package org.openurp.prac.innovation.model

import org.beangle.data.model.IntId
import org.beangle.data.model.pojo.{Coded, Hierarchical, Named, TemporalOn}

object StageType {
  val Initial = 1
  val Closure = 3
  val ApplyExemptionReply = 4 //这是结项的一个子阶段
  val PromotionState = 5
  val PromotionNation = 6
}

class StageType extends IntId, Named, Coded, TemporalOn, Hierarchical[StageType] {

  def this(id: Int) = {
    this()
    this.id = id
  }
}
