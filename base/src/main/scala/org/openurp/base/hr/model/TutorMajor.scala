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

package org.openurp.base.hr.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.{Direction, Major}
import org.openurp.code.edu.model.{EducationLevel, EducationType}

import scala.collection.mutable

/** 导师研究领域
 */
class TutorMajor extends LongId, Remark {

  /** 导师 */
  var staff: Staff = _

  /** 培养类型 */
  var eduType: EducationType = _

  /** 培养层次 */
  var level: EducationLevel = _

  /** 专业 */
  var major: Major = _

  /** 方向 */
  var directions: mutable.Set[Direction] = Collections.newSet[Direction]
}
