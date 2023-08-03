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

package org.openurp.edu.clazz.model

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student

/** 学生每学期选择教学班限制和统计
 */
class StdCreditStat extends LongId {

  var std: Student = _

  var semester: Semester = _

  /** 已选学分 */
  var totalCredits: Float = _

  /** 学分上限 */
  var maxCredits: Float = _

  /** 已选新课程门数
   * 不含重修 */
  var totalNewCount: Int = _

  /** 最多新选课程门数 */
  var maxNewCount: Int = _

  /** 重修数量 */
  var repeatCount: Int = _
}
