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

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.Certificate

/**
 * 资格考试报名记录
 *
 * @author chaostone
 */
class CertSignup extends LongId with Updated {
  /** 学生 */
  var std: Student = _
  /** 学年学期 */
  var semester: Semester = _
  /** 报名证书 */
  var certificate: Certificate = _
  /** 报名费 */
  var fee: Int = _
  /** 准考证号码 */
  var examNo: Option[String] = None
  /** 考场 */
  var examRoom: Option[String] = None
  /** 座位号 */
  var seatNo: Int = _
  /** 报名IP */
  var ip: String = _
}
