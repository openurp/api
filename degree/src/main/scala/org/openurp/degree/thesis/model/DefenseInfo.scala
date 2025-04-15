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

import java.time.LocalDate

class DefenseInfo extends LongId, Updated {

  /** 学生 */
  var writer: Writer = _

  /** 答辩成绩 */
  var defenseScore: Option[Int] = None

  /** 回答问题摘要 */
  var questions: Option[String] = None

  /** 答辩记录人 */
  var recorder: Option[String] = None

  /** 答辩小组评语 */
  var groupOpinion: Option[String] = None

  /** 论文概述情况 */
  var thesisSummaryScore: Option[Int] = None

  /** 回答问题情况 */
  var answerSummaryScore: Option[Int] = None

  /** 答辩日期 */
  var defenseOn: Option[LocalDate] = None
}
