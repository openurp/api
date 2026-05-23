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

import org.beangle.commons.json.{Json, JsonObject}
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.Project
import org.openurp.base.std.model.GraduateSeason

import java.time.{Instant, LocalDate}

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

  /** 申请起始时间 */
  var applyBeginAt: Option[Instant] = None

  /** 申请结束时间 */
  var applyEndAt: Option[Instant] = None

  /** 设置 */
  var settings: JsonObject = Json.emptyObject

  /**
   * 是否在该时间内开放
   *
   * @param now
   * @return
   */
  def applyWithin(now: Instant): Boolean = {
    if (applyBeginAt.nonEmpty && applyEndAt.nonEmpty) {
      !(applyBeginAt.get.isAfter(now) || applyEndAt.get.isBefore(now))
    } else {
      false
    }
  }
}
