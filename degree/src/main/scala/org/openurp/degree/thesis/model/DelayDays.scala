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

import scala.collection.mutable

object DelayDays {
  private val days = new mutable.HashMap[Stage, Int]

  def dayOf(stage: Stage): Int = {
    days(stage)
  }

  Stage.values foreach { s =>
    days.put(s, 0)
  }
  days.put(Stage.Commitment, 3)
  days.put(Stage.Proposal, 3)
  days.put(Stage.MidtermCheck, 3)
  days.put(Stage.ThesisDraftSubmit, 3)

}
