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

package org.openurp.edu.exam.flow

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.flow
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{ExamDeferReason, ExamType}
import org.openurp.edu.clazz.model.Clazz

import java.time.Instant

/** 考试缓考申请
 */
@flow
class ExamDeferApply extends LongId with Updated with Remark {

  var clazz: Clazz = _

  var std: Student = _

  var examType: ExamType = _

  var examBeginAt: Option[Instant] = None

  var mobile: Option[String] = None

  var reason: Option[ExamDeferReason] = None

  var passed: Option[Boolean] = None

  var status: String = _
}
