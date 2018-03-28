/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.code
import org.beangle.data.model.pojo.{ Coded, Named, Remark, TemporalOn, Updated }
import org.openurp.base.model.{ Campus, Department }
import org.openurp.edu.base.EducationBased
import org.openurp.edu.base.code.model.StdType

/**
 * 学生行政班级信息
 *
 * @author chaostone
 * @since 2005-9-12
 */
class Adminclass extends LongId with EducationBased with Coded with Named with TemporalOn with Updated with Remark {

  /** 年级,形式为yyyy-p */
  var grade: String = _
  /** 简称 */
  var shortName: Option[String] = None
  /** 院系 */
  var department: Department = _
  /** 专业 */
  var major: Option[Major] = None
  /** 方向 */
  var direction: Option[Direction] = None
  /** 学生类别 */
  var stdType: StdType = _
  /** 计划人数 */
  var planCount: Int = _
  /** 学籍有效人数 */
  var stdCount: Int = _
  /** 辅导员 */
  var instructor: Option[Instructor] = None
  /** 班导师 */
  var tutor: Option[Teacher] = None
  /** 学生状态 */
  var stdStates = Collections.newBuffer[StudentState]
  /**培养方案*/
  var program: Option[Program] = None
  /**固定校区*/
  var campus: Campus = _
}
