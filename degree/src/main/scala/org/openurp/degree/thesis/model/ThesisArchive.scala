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

package org.openurp.degree.thesis.model

import org.beangle.data.model.LongId
import org.openurp.base.model.User

import java.time.Instant

/** 论文归档信息
 */
class ThesisArchive extends LongId {
  /** 作者 */
  var writer: Writer = _
  /** 指导老师是否确认 */
  var confirmed: Option[Boolean] = None
  /** 确认人 */
  var confirmedBy: Option[User] = None
  /** 确认时间 */
  var confirmAt: Option[Instant] = None
  /** 是否归档 */
  var archived: Option[Boolean] = None
  /** 意见反馈 */
  var feedback: Option[String] = None
  /** 上传时间 */
  var uploadAt: Option[Instant] = None

  def this(writer: Writer) = {
    this()
    this.writer = writer
  }
}
