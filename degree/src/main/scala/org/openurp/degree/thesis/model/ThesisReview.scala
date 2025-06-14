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
import org.beangle.data.model.pojo.Remark
import org.openurp.base.hr.model.Teacher

import java.time.Instant

/** 论文评分 */
class ThesisReview extends LongId, Remark {

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

  /** 校外送审成绩 */
  var blindReviewScore: Option[Float] = None

  /** 指导教师自己打分 */
  var advisorSelfScore: Option[Float] = None

  /** 指导教师得分 */
  var advisorScore: Option[Int] = None

  /** 指导教师评分时间 */
  var advisorReviewAt: Option[Instant] = None

  /** 交叉评阅负责人 */
  var crossReviewManager: Option[Teacher] = None

  /** 交叉评阅人 */
  var crossReviewer: Option[Teacher] = None

  /** 交叉评阅得分 */
  var crossReviewScore: Option[Float] = None

  /** 交叉评阅意见 */
  var crossReviewOpinion: Option[String] = None

  /** 交叉评阅时间 */
  var crossReviewAt: Option[Instant] = None

  /** 是否同意答辩 */
  var defensePermitted: Option[Boolean] = None

  /** 答辩成绩 */
  var defenseScore: Option[Float] = None

  /** 最终成绩 */
  var finalScore: Option[Float] = None

  /** 最终成绩五级制 */
  var finalScoreText: Option[String] = None

  /** 答辩信息 */
  var defenseInfo: Option[DefenseInfo] = None

  /** 成绩是否同步到课程成绩 */
  var courseGradeSynced: Boolean = _
}
