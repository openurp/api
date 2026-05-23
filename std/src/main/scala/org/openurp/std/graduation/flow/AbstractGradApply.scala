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
import org.openurp.std.graduation.model.GraduateBatch

/** 毕业申请抽象类
 */
@flow
abstract class AbstractGradApply extends LongId, Updated {

  var batch: GraduateBatch = _

  var std: Student = _

  /** 申请理由 */
  var reason: Option[String] = None

  /** 联系手机 */
  var mobile: Option[String] = None

  /** 院系是否通过 */
  var collegePassed: Option[Boolean] = None

  /** 院系意见 */
  var collegeOpinion: Option[String] = None

  /** 是否通过 */
  var passed: Option[Boolean] = None

  /** 其他申请数据 */
  var datas: JsonObject = Json.emptyObject

  /** 个人签名url */
  var stdSignUrl: Option[String] = None

}
