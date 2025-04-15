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

package org.openurp.std.award.code

import org.beangle.data.model.IntId
import org.beangle.data.model.annotation.code
import org.beangle.data.model.pojo.{Coded, Named, TemporalOn}

@code("school")
class ScholarshipCategory extends IntId, Coded, Named, TemporalOn {

  /** 奖学金类型 */
  var scholarshipType: ScholarshipType = _

  /** 奖学金描述 */
  var discription: Option[String] = None

  /** 评定周期 */
  var assessPeriod: String = _

  /** 颁奖单位 */
  var awardUnit: String = _

  /** 使用状态 */
  var enabled: Boolean = _

  /** 是否分等级 */
  var rated: Boolean = _

}

@code("school")
class ScholarshipLevel extends IntId, Coded, Named {

  /** 奖学金种类 */
  var category: ScholarshipCategory = _

  /** 奖励金额 */
  var amount: Int = _

  /** 使用状态 */
  var enabled: Boolean = _

  /** 描述 */
  var discription: Option[String] = None
}

@code("school")
class ScholarshipType extends IntId , Coded , Named {
  /** 使用状态 */
  var enabled: Boolean = _
  /** 排序序号 */
  var idx: String = _
}
