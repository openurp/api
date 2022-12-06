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

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.EducationResult

import scala.collection.mutable

/**
 * 毕业审核结果
 */
class GraduateResult extends LongId with Updated {

  /** 所属的毕业审核批次 */
  var batch: GraduateBatch = _

  /** 批次 */
  var batchNo: Int = _

  /** 学生 */
  var std: Student = _

  /** 毕业审核详细结果 */
  var items: mutable.Buffer[GraduateAuditItem] = Collections.newBuffer[GraduateAuditItem]

  /** 获得学分 */
  var acquiredCredits: Float = _

  /** 要求学分 */
  var requiredCredits: Float = _

  /** 修读学分 */
  var electedCredits: Float = _

  /** 是否通过毕业审核 */
  var passed: Option[Boolean] = None

  /** 锁定毕业审核结果 */
  var locked: Boolean = _

  /** 是否已发布 */
  var published: Boolean = _

  /** 毕业备注 */
  var comments: Option[String] = None

  /** 毕结业情况 */
  var educationResult: Option[EducationResult] = None
}
