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

package org.openurp.std.graduation.model

import org.beangle.data.model.LongId
import org.openurp.base.std.model.Student
import org.openurp.code.std.model.GraduateType

/** 预毕业信息
 */
class Graduation extends LongId {

  /** 学籍 */
  var std: Student = _

  /** 批次 */
  var batch: GraduateBatch = _

  /** 毕业生类型 */
  var graduateType: GraduateType = _

  /** 是否审核学位 */
  var hasDegree: Boolean = _
}
