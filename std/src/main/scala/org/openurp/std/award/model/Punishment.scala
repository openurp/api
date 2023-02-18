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

;

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Named, Remark}
import org.openurp.base.model.{Department, Semester}
import org.openurp.base.std.model.Student
import org.openurp.code.std.model.StdPunishmentType

import java.time.LocalDate

/**
 * 处分记录
 */
class Punishment extends LongId with Named with Remark {

  /** 处分文号 */
  var docSeq: String = _

  /** 处分类别 */
  var publishmentType: StdPunishmentType = _

  /** 学生 */
  var std: Student = _

  /** 教学日历 */
  var semester: Semester = _

  /** 日期 */
  var issueOn: LocalDate = _

  /** 撤销日期 */
  var withdrawOn: Option[LocalDate] = None

  /** 部门 */
  var depart: Department = _

  /** 处分原因 */
  var reason: Option[String] = None

}
