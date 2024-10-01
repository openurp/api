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
import org.beangle.data.model.pojo.Named
import org.openurp.base.model.AuditStatus
import org.openurp.code.person.model.Language

import java.time.Instant

/** 毕业论文信息
 */
class ThesisPaper extends LongId with Named {

  var writer: Writer = _

  /** 题目 */
  var title: String = _

  /** 状态 */
  var status: AuditStatus = _

  /** 论文关键词 */
  var keywords: Option[String] = None

  /** 研究领域 */
  var researchField: Option[String] = None

  /** 撰写语种 */
  var language: Option[Language] = None

  /** 毕业论文类型 */
  var thesisType: Option[String] = None

  /** 初稿路径 */
  var draftPath: Option[String] = None

  /** 附件路径 */
  var filePath: String = _

  /** 是否定稿 */
  var finalized: Boolean = _

  /** sha1sum */
  var sha1sum: String = _

  /** 提交时间 */
  var submitAt: Instant = _

  /** 导师是否通过 */
  var advisorPassed: Option[Boolean] = None

  /** 审核意见 */
  var auditOpinion: Option[String] = None

  /** 反抄袭检测 */
  var copyCheck: Option[CopyCheck] = None

  /** 反抄袭复检 */
  var recheck: Option[CopyCheck] = None

  /** 优秀论文 */
  var excellent: Boolean = _
}
