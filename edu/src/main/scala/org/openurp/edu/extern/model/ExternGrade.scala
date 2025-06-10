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

package org.openurp.edu.extern.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.edu.model.Course
import org.openurp.base.model.AuditStatus
import org.openurp.base.std.model.ExternStudent

import java.time.{LocalDate, YearMonth}
import scala.collection.mutable

/**
 * 外校学习成绩
 */
class ExternGrade extends LongId, Remark, Updated {

  var externStudent: ExternStudent = _

  var courseName: String = _

  var credits: Float = _

  /** 获得年月 */
  var acquiredIn: YearMonth = _

  var scoreText: String = _

  var exempts: mutable.Set[Course] = Collections.newSet[Course]

  var status: AuditStatus = AuditStatus.Draft
}
