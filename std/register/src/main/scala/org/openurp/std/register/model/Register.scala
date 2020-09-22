/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.std.register.model

import java.time.Instant

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.code.std.model.{UncheckinReason, UnregisteredReason}
import org.openurp.edu.base.model.Semester
import org.openurp.edu.base.model.Student

class Register extends LongId with Remark {

  var std: Student = _

  var semester: Semester = _

  var registerAt: Option[Instant] = None

  var registered: Option[Boolean] = None

  var checkin: Option[Boolean] = None

  /** 是否缴费 */
  var tuitionPaid: Option[Boolean] = None

  /** 未注册原因 */
  var unregisteredReason: Option[UnregisteredReason] = None

  /** 未报到原因 */
  var uncheckinReason: Option[UncheckinReason] = None

  /** 操作人 */
  var operateBy: String = _

  /** 操作ip */
  var operateIp: String = _

}
