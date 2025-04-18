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
import org.beangle.data.model.pojo.Updated
import org.openurp.base.edu.model.Major
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{EducationMode, EnrollMode}
import org.openurp.code.geo.model.Division

import java.time.LocalDate

/**
 * 考生信息
 */
class Examinee extends LongId, Updated {

  /** 学生 */
  var std: Student = _

  /** 考生号 */
  var code: Option[String] = None

  /** 准考证号 */
  var examNo: Option[String] = None

  /** 生源地 */
  var originDivision: Option[Division] = None

  /** 毕业学校名称 */
  var schoolName: Option[String] = None

  /** 毕业日期 */
  var graduateOn: Option[LocalDate] = None

  /** 录取专业 */
  var major: Option[Major] = None

  /** 录取通知书号 */
  var letterNo: Option[String] = None

  /** 招生录取总分 */
  var score: Option[Float] = None

  /** 各科分数 科目->浮点数 */
  var scores: Option[String] = None

  /** 入学方式 */
  var enrollMode: Option[EnrollMode] = None

  /** 培养方式 */
  var educationMode: Option[EducationMode] = None

  /** 委培单位 */
  var client: Option[String] = None
}
