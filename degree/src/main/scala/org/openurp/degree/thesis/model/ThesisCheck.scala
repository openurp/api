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
import org.openurp.base.std.model.GraduateSeason
import org.openurp.code.person.model.Language

import java.time.YearMonth

/** 论文抽检
 */
class ThesisCheck extends LongId, Updated {
  /** 毕业季 */
  var season: GraduateSeason = _

  /** 学生 */
  var writer: Writer = _

  /** 学生姓名 */
  var writerName: String = _

  /** 入学年月 */
  var enrollOn: YearMonth = _

  /** 毕业年月 */
  var graduateOn: YearMonth = _

  /**考生号*/
  var examineeCode:Option[String]=None

  /** 学位专业代码 */
  var degreeMajorCode: String = _

  /** 学位专业名称 */
  var degreeMajorName: String = _

  /** 证书专业名称 */
  var certMajorName: String = _

  /** 是否主辅修学位 */
  var majorMinorDegree: Option[Boolean] = None

  /** 是否辅修学位 */
  var minorDegree: Option[Boolean] = None

  /** 是否双学位 */
  var dualDegree: Option[Boolean] = None

  /** 是否第二学位 */
  var secondDegree: Option[Boolean] = None

  /** 是否联合学位 */
  var jointDegree: Option[Boolean] = None

  /** 联合培养单位 */
  var jointOrgCode: Option[String] = None

  /** 学位类型 */
  var eduType: Option[String] = None

  /** 指导老师 */
  var advisor: Option[String] = None

  /** 题目 */
  var title: String = _

  /** 是否第一届毕业生 */
  var firstSeason: Boolean = _

  /** 毕业论文类型 */
  var thesisType: Option[String] = None

  /** 论文关键词 */
  var keywords: Option[String] = None

  /** 研究领域 */
  var researchField: Option[String] = None

  /** 撰写语种 */
  var language: Option[Language] = None

  /** 论文封面 */
  var coverDoc: Option[ThesisDoc] = None

  /** 论文 */
  var paperDoc: Option[ThesisDoc] = None

  /** 开题报告 */
  var proposalDoc: Option[ThesisDoc] = None

  /** 答辩评分表 */
  var defenseDoc: Option[ThesisDoc] = None

  /**部门代码*/
  var departNo: Option[String] = None

  /**部门名称*/
  var departName: Option[String] = None
}
