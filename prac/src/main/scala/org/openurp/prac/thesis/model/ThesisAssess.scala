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

package org.openurp.prac.thesis.model

import org.beangle.data.model.LongId

/** 论文评分 */
class ThesisAssess extends LongId {

  /** 学生 */
  var writer: Writer = _

  /** 选题得分 */
  var subjectScore: Int = _

  /** 写作规范 */
  var writeScore: Int = _

  /** 研究能力 */
  var researchScore: Int = _

  /** 创新水平 */
  var innovationScore: Int = _

  /** 写作态度 */
  var attitudeScore: Int = _

  /** 知道教师得分 */
  var teacherScore: Option[Int] = None

  /** 交叉评阅得分 */
  var assessScore: Option[Int] = None

  /** 交叉评阅意见 */
  var assessOpinion: Option[String] = None

  /** 是否同意答辩 */
  var replyPermited: Option[Boolean] = None

  /** 答辩情况 */
  var replyInfo: Option[ReplyInfo] = None

  /** 答辩成绩 */
  var replyScore: Option[Int] = None

  /** 最终成绩 */
  var finalScore: Option[Int] = None

}
