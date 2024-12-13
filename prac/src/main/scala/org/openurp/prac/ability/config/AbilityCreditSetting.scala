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

package org.openurp.prac.ability.config

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.Major
import org.openurp.base.model.Department
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.Certificate

import scala.collection.mutable

/** 能力拓展学分证书配置
 */
@config
class AbilityCreditSetting extends LongId with Remark {
  /** 认定设置 */
  var config: AbilityCreditConfig = _
  /** 证书类型 */
  var certificate: Certificate = _
  /** 审核学院 */
  var auditDepart: Option[Department] = None
  /** 是否需要学院审核 */
  var collegeReviewRequired: Boolean = _
  /** 有效期长度，以月为单位 */
  var validMonths: Option[Int] = None
  /** 学分数 */
  var credits: Float = _
  /** 学科专业特定还是全校通用 */
  var special: Boolean = _
  /** 面向学院 */
  var departs: mutable.Set[Department] = Collections.newSet[Department]
  /** 面向专业 */
  var majors: mutable.Set[Major] = Collections.newSet[Major]

  def suitable(std: Student): Boolean = {
    if (departs.nonEmpty) {
      if (!departs.contains(std.department)) return false
    }
    if (majors.nonEmpty) {
      if (!majors.contains(std.major)) return false
    }
    true
  }
}
