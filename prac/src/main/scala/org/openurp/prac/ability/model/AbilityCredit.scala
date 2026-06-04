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

package org.openurp.prac.ability.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updatable}
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.Certificate

import java.time.YearMonth

/** 学生能力素质能力证书
 */
class AbilityCredit extends LongId, Updatable, Remark {
  /** 学生 */
  var std: Student = _
  /** 申请学期 */
  var semester: Semester = _
  /** 证书类型 */
  var certificate: Certificate = _
  /** 证书内课程 */
  var subjects: String = _
  /** 证书编号 */
  var certificateNo: String = _
  /** 获得年月 */
  var acquiredIn: YearMonth = _
  /** 认定的学分数 */
  var credits: Float = _

  def this(std: Student) = {
    this()
    this.std = std
  }
}
