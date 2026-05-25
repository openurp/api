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

package org.openurp.std.graduation.model

import org.beangle.data.model.LongId
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.Degree
import org.openurp.code.std.model.{GraduateType, GraduationStatus}

/** 预毕业信息
 */
class Graduation extends LongId {

  /** 学籍 */
  var std: Student = _

  /** 批次 */
  var batch: GraduateBatch = _

  /** 平均绩点 */
  var gpa: Double = _

  /** 平均分 */
  var wms: Double = _

  /** 是否能完成计划 */
  var planPassed: Boolean = _

  /** 毕业审核是否通过 */
  var gradPassed: Option[Boolean] = None

  /** 学位审核是否通过 */
  var degreePassed: Option[Boolean] = None

  /** 对应学位类型 */
  var degree: Option[Degree] = None

  /** 是否申请了学位 */
  var degreeApplied: Boolean = _

  /** 申请的毕业状态 */
  var applyStatus: Option[GraduationStatus] = None

  /** 毕业生类型 */
  var graduateType: GraduateType = _

  /** 联系手机 */
  var mobile: Option[String] = None

  /** 联系邮箱 */
  var email: Option[String] = None

  /** 手机号码是否已经验证 */
  var mobileVerified: Boolean = _

  /** 毕业状态 */
  var status: Option[GraduationStatus] = None

}
