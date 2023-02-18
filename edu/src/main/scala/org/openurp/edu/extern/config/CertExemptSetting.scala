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

package org.openurp.edu.extern.config

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.{InstantRange, Remark}
import org.openurp.base.edu.model.Course
import org.openurp.base.model.Department
import org.openurp.edu.extern.code.CertificateSubject

import scala.collection.mutable

/**
 * 校外考试免修科目设置
 */
@config
class CertExemptSetting extends LongId with Remark {

  var config: CertExemptConfig = _
  /** 考试科目 */
  var subject: CertificateSubject = _
  /** 审核部门 */
  var auditDepart: Department = _
  /**
   * 有效期长度，以月为单位
   */
  var validMonths: Option[Int] = None
  /** 最低分 */
  var minScore: Option[Float] = None
  /** 免修课程 */
  var courses: mutable.Set[Course] = Collections.newSet[Course]
  /** 免修最大门数 */
  var maxCount: Int = _
}
