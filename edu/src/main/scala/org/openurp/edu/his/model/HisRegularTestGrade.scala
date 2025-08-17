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

package org.openurp.edu.his.model

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.archive
import org.openurp.base.model.ArchivedByYear
import org.openurp.edu.grade.model.RegularComponent

/** 归档平时测试成绩 */
@archive
class HisRegularTestGrade extends LongId, ArchivedByYear {
  /** 平时成绩 */
  var regularGrade: HisRegularGrade = _
  /** 测验类型 */
  var component: RegularComponent = _
  /** 百分比 */
  var scorePercent: Int = _
  /** 分数 */
  var score: Float = _
  /** 过程明细 */
  var details: Option[String] = None
}
