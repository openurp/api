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

package org.openurp.std.fee.config

import org.beangle.data.model.IntId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.{Direction, Major}
import org.openurp.base.model.{Department, Project}
import org.openurp.base.std.model.Grade
import org.openurp.code.edu.model.EducationLevel
import org.openurp.code.std.model.FeeType

/**
 * 收费缺省值
 */
@config
class TuitionConfig extends IntId, Remark {

  var project: Project = _

  /** 起始年级 */
  var fromGrade: Grade = _

  /** 截止年级 */
  var toGrade: Option[Grade] = None

  /** 学制 */
  var duration: Float = _

  /** 学历层次 */
  var level: EducationLevel = _

  /** 院系 */
  var department: Option[Department] = None

  /** 所属的专业 */
  var major: Option[Major] = None

  /** 所属的专业方向 */
  var direction: Option[Direction] = None

  /** 收费类型 */
  var feeType: FeeType = _

  /** 对应的值 */
  var amount: Int = _

}
