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
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.AuditStatus

import java.time.Instant

/** 开题报告
 */
class Proposal extends LongId {

  /** 学生 */
  var writer: Writer = _

  /** 目的意义 */
  var meanings: String = _

  /** 现状 */
  var conditions: String = _

  /** 论文提纲 */
  var outline: String = _

  /** 参考文献 */
  var references: String = _

  /** 研究方法 */
  var methods: String = _

  /** 学生提交随时间 */
  var submitAt: Instant = _
  /** 审查状态 */
  var status: AuditStatus = AuditStatus.Draft
  /** 教师审核意见 */
  var advisorOpinion: Option[String] = None
  /** 确认时间 */
  var confirmAt: Option[Instant] = None
}
