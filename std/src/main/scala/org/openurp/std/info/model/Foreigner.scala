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

package org.openurp.std.info.model

import org.beangle.data.model.LongId
import org.openurp.base.std.model.Student

import java.time.LocalDate

/** 留学生信息
 */
class Foreigner extends LongId {
  /** 学生 */
  var std: Student = _

  /** 护照编号 */
  var passportNo: Option[String] = None

  /** 签证编号 */
  var visaNo: Option[String] = None

  /** CSC编号 */
  var cscNo: Option[String] = None

  /** 居住许可证编号 */
  var residenceNo: Option[String] = None

  /** 护照到期时间 */
  var passportExpired: Option[LocalDate] = None

  /** 签证到期时间 */
  var visaExpired: Option[LocalDate] = None

  /** 居住许可证到期时间 */
  var residenceExpired: Option[LocalDate] = None

}
