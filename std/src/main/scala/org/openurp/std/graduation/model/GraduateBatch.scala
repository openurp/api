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
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.Project
import org.openurp.base.std.model.GraduateSeason

import java.time.LocalDate

/** 毕业批次
 */
class GraduateBatch extends LongId, Updated {

  var project: Project = _

  /** 毕业界别 */
  var season: GraduateSeason = _

  /** 名称 */
  var name: String = _

  /** 是否授学位 */
  var degreeOffered: Boolean = _

  /** 毕业日期 */
  var graduateOn: LocalDate = _

  /** 是否启用计划完成情况确认 */
  var enableProgressConfirm: Boolean = false
}
