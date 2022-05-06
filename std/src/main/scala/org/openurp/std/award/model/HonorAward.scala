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

package org.openurp.std.award.model

import org.beangle.data.model.annotation.code
import org.beangle.data.model.{IntId, LongId}
import org.beangle.data.model.pojo.{Coded, Named}
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student

/** 荣誉奖学金
 *
 */
class HonorAward extends LongId {

  /**荣誉种类*/
  var category: HonorCategory = _

  /**学生*/
  var std: Student = _

  /**获奖等级*/
  var level: HonorLevel = _

  /**评定学期*/
  var semester: Semester = _

  /**金额*/
  var amount: Int = _

  /**是否审核通过*/
  var approved: Boolean = _

}

@code("school")
class HonorCategory extends IntId with Coded with Named {

  /**荣誉类型*/
  var honorType: HonorType = _

  /**荣誉描述*/
  var discription: Option[String] = None

  /**评定周期*/
  var assessPeriod: String = _

  /**颁奖单位*/
  var awardUnit: String = _

  /**使用状态*/
  var enabled: Boolean = _

  /**是否分等级*/
  var rated: Boolean = _

}

@code("school")
class HonorLevel extends IntId with Coded with Named {

  /**荣誉种类*/
  var category: HonorCategory = _

  /**奖励金额*/
  var amount: Int = _

  /**使用状态*/
  var enabled: Boolean = _

  /**描述*/
  var discription: Option[String] = None
}

@code("school")
class HonorType extends IntId with Coded with Named {
  /**使用状态*/
  var enabled: Boolean = _
  /**排序序号*/
  var idx: String = _
}
