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

package org.openurp.std.graduation.flow

import org.beangle.commons.json.{Json, JsonObject}
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.flow
import org.beangle.data.model.pojo.Updated
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.Degree
import org.openurp.std.graduation.flow.AbstractGradApply
import org.openurp.std.graduation.model.GraduateBatch

import java.time.Instant

/** 学位申请数据
 */
@flow
class GradDegreeApply extends AbstractGradApply {

  /** 平均绩点 */
  var gpa: Double = _

  /** 平均分 */
  var wms: Double = _

  /** 学位类型 */
  var degree: Degree = _

  /** 联系邮箱 */
  var email: Option[String] = None

  def this(batch: GraduateBatch, std: Student) = {
    this()
    this.batch = batch
    this.std = std
    this.updatedAt = Instant.now
  }
}
