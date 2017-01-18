/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.award.code.model

import org.beangle.commons.model.{ Coded, IntId, Named, TemporalOn }
import org.beangle.commons.model.annotation.code

@code("school")
class StipendCategory extends IntId with Coded with Named with TemporalOn {

  /**助学金描述*/
  var discription: Option[String] = None

  /**评定周期*/
  var period: String = _

  /**颁奖单位*/
  var awardUnit: String = _

  /**使用状态*/
  var enabled: Boolean = _

  /**是否分等级*/
  var beRated: Boolean = _

}

@code("school")
class StipendLevel extends IntId with Coded with Named {

  /**助学金种类*/
  var stipendCategory: StipendCategory = _

  /**奖励金额*/
  var awardAmount: Float = _

  /**使用状态*/
  var enabled: Boolean = _

  /**描述*/
  var discription: Option[String] = None
}
