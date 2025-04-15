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

package org.openurp.edu.exempt.config

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.Course
import org.openurp.base.model.Department
import org.openurp.code.edu.model.Certificate

import scala.collection.mutable

/**
 * 校外证书免修科目设置
 */
@config
class CertExemptSetting extends LongId, Remark {
  /** 免修设置 */
  var config: CertExemptConfig = _
  /** 证书类型 */
  var certificate: Certificate = _

  /** 是否需要学院审核 */
  var collegeReviewRequired: Boolean = _
  /** 审核部门 */
  var auditDepart: Option[Department] = None

  /** 有效期长度，以月为单位 */
  var validMonths: Option[Int] = None
  /** 最低分 */
  var minScore: Option[Float] = None

  /** 认定得分表达式 */
  var scoreExpr: Option[String] = None
  /** 免修课程 */
  var courses: mutable.Set[Course] = Collections.newSet[Course]
  /** 免修最大门数 */
  var maxCount: Int = _
}
