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

package org.openurp.std.graduation.flow

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.flow
import org.beangle.data.model.pojo.Updated
import org.openurp.base.std.model.Student
import org.openurp.std.graduation.model.GraduateBatch

/** 本科学生第二学士学位学位申请
 */
@flow
class GradBachelor2ndApply extends LongId, Updated {

  /** 毕业批次 */
  var batch: GraduateBatch = _

  /** 学生 */
  var std: Student = _

  /** 平均绩点 */
  var gpa: Float = _

  /** 成绩明细 */
  var gradeDetail: String = _
}
