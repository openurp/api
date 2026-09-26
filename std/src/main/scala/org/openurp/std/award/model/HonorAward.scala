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
import org.openurp.std.award.code.{HonorCategory, HonorLevel}
import scala.compiletime.uninitialized

/** 荣誉奖学金
 *
 */
class HonorAward extends LongId {
  /**荣誉种类*/
  var category: HonorCategory = uninitialized
  /**学生*/
  var std: Student = uninitialized
  /**获奖等级*/
  var level: HonorLevel = uninitialized
  /**评定学期*/
  var semester: Semester = uninitialized
  /**金额*/
  var amount: Int = uninitialized
  /**是否审核通过*/
  var approved: Boolean = uninitialized

}
