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

package org.openurp.prac.activity.model

import org.openurp.code.prac.model.PracActivityType

/** 校外实践活动
 */
class PracActivity extends AbstractPracActivity {
  /** 活动名称 */
  var activityName: String = _
  /** 活动类型 */
  var activityType: PracActivityType = _
  /** 活动介绍 */
  var description: String = _

  def addSchedule(ns: AbstractPracSchedule): Unit = {
    this.schedules += ns
    ns.asInstanceOf[PracActivitySchedule].activity = this
  }
}
