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

package org.openurp.base.std.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.*
import org.openurp.base.edu.model.{Direction, Major, Teacher}
import org.openurp.base.model.*
import org.openurp.base.std.code.StdType
import org.openurp.base.std.model.{Mentor, StudentState}

import scala.collection.mutable

/**
 * 学生行政班级信息
 *
 * @author chaostone
 * @since 2005-9-12
 */
class Squad extends LongId with EduLevelBased with Coded with Named with DateRange with Updated with Remark {

  /** 年级,形式为yyyy-p */
  var grade: Grade = _
  /** 简称 */
  var shortName: Option[String] = None
  /** 专业英文名 */
  var enName: Option[String] = None
  /** 院系 */
  var department: Department = _
  /** 专业 */
  var major: Option[Major] = None
  /** 方向 */
  var direction: Option[Direction] = None
  /** 学生类别 */
  var stdType: Option[StdType] = None
  /** 计划人数 */
  var planCount: Int = _
  /** 学籍有效人数 */
  var stdCount: Int = _
  /** 辅导员 */
  var mentor: Option[Staff] = None
  /** 班导师 */
  var tutor: Option[Staff] = None
  /** 班主任 */
  var master: Option[Staff] = None
  /** 学生状态 */
  var stdStates: mutable.Buffer[StudentState] = Collections.newBuffer[StudentState]
  /** 固定校区 */
  var campus: Campus = _
}
