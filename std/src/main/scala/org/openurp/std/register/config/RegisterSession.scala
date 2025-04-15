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

package org.openurp.std.register.config

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.InstantRange
import org.openurp.base.model.{Project, Semester}
import org.openurp.code.edu.model.EducationLevel

import java.time.Instant

/**
 * 注册批次
 */
@config
class RegisterSession extends LongId, InstantRange {

  var project: Project = _

  var semester: Semester = _

  var grades: String = _

  var level: EducationLevel = _

  def canApply(): Boolean = {
    val now = Instant.now
    !(this.beginAt.isAfter(now) || this.endAt.isBefore(now))
  }
}
