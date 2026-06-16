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

import org.beangle.commons.json.JsonObject
import org.beangle.data.model.LongId
import org.openurp.base.std.model.{GraduateSeason, Student}

import java.time.Instant

/**
 * 正常毕业的毕业生
 */
class DelayedGraduate extends LongId {

  /** 毕业界别 */
  var season: GraduateSeason = _

  /** 学籍 */
  var std: Student = _

  /** 是否告知 */
  var informed: Option[Boolean] = None

  /** 告知详情 */
  var inform_details: JsonObject = new JsonObject()

  /** 是否知晓 */
  var acknowledged: Option[Boolean] = None

  /** 知晓时间 */
  var acknowledgedAt: Option[Instant] = None

  /** 个人签名url */
  var stdSignUrl: Option[String] = None
}
