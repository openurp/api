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

package org.openurp.base.std.model

import org.beangle.data.model.LongId
import org.openurp.code.edu.model.{ThesisTopicSource, ThesisType}
import org.openurp.code.person.model.Language

/** 学生论文信息
 */
class Thesis extends LongId {

  /** 学籍 */
  var std: Student = _

  /** 论文题目 */
  var title: String = _

  /** 指导老师 */
  var advisor: Option[String] = None

  /** 分数 */
  var score: Option[Float] = None

  /** 成绩 */
  var scoreText: Option[String] = None

  /** 论文关键词 */
  var keywords: Option[String] = None

  /** 研究领域 */
  var researchField: Option[String] = None

  /** 撰写语种 */
  var language: Option[Language] = None

  /** 毕业论文类型 */
  var thesisType: Option[ThesisType] = None

  /** 选题来源 */
  var source: Option[ThesisTopicSource] = None

  /** 评语 */
  var comments: Option[String] = None
}
