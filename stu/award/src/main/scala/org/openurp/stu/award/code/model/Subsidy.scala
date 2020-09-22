/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.stu.award.code.model

import org.beangle.data.model.IntId
import org.beangle.data.model.annotation.code
import org.beangle.data.model.pojo.{ Coded, Named }

@code("school")
class SubsidyCategory extends IntId with Coded with Named {

  /**困难补助描述*/
  var discription: Option[String] = None

  /**评定周期*/
  var assessPeriod: String = _

  /**颁奖单位*/
  var awardUnit: String = _

  /**使用状态*/
  var enabled: Boolean = _

  /**是否分等级*/
  var beRated: Boolean = _

}

@code("school")
class SubsidyLevel extends IntId with Coded with Named {

  /**困难补助种类*/
  var subsidyCategory: SubsidyCategory = _

  /**奖励金额*/
  var awardAmount: Float = _

  /**使用状态*/
  var enabled: Boolean = _

  /**描述*/
  var discription: Option[String] = None
}
