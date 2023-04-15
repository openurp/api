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
import org.openurp.base.edu.model.Teacher

import java.time.Instant

/** 论文评分 */
class ThesisReview extends LongId {

  /** 学生 */
  var writer: Writer = _

  /** 选题得分 */
  var subjectScore: Option[Int] = None

  /** 写作规范 */
  var writeScore: Option[Int] = None

  /** 研究能力 */
  var researchScore: Option[Int] = None

  /** 创新水平 */
  var innovationScore: Option[Int] = None

  /** 写作态度 */
  var attitudeScore: Option[Int] = None

  /** 指导教师得分 */
  var advisorScore: Option[Int] = None

  /**指导教师评分时间*/
  var advisorReviewAt: Option[Instant] = None

  /** 交叉评阅负责人 */
  var crossReviewManager: Option[Teacher] = None

  /** 交叉评阅人 */
  var crossReviewer: Option[Teacher] = None

  /** 交叉评阅得分 */
  var crossReviewScore: Option[Int] = None

  /** 交叉评阅意见 */
  var crossReviewOpinion: Option[String] = None

  /**交叉评阅时间*/
  var crossReviewAt:Option[Instant] = None

  /** 是否同意答辩 */
  var defensePermited: Option[Boolean] = None

  /** 答辩成绩 */
  var defenseScore: Option[Int] = None

  /** 最终成绩 */
  var finalScore: Option[Int] = None

  var defenseInfo: Option[DefenseInfo] = None
}
