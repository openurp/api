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

import org.beangle.data.model.annotation.flow
import org.openurp.base.std.model.Student
import org.openurp.std.graduation.flow.AbstractGradApply
import org.openurp.std.graduation.model.GraduateBatch

import java.time.{Instant, LocalDate}

/** 延期申请
 */
@flow
class GradDeferApply extends AbstractGradApply {

  /** 计划毕业日期 */
  var planGradDate: LocalDate = _

  /** 申请延期到 */
  var deferGradDate: LocalDate = _

  def this(batch: GraduateBatch, std: Student) = {
    this()
    this.batch = batch
    this.std = std
    this.updatedAt = Instant.now
  }
}
