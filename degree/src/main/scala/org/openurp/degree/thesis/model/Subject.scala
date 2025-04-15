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
import org.openurp.base.edu.model.Major
import org.openurp.base.model.{AuditStatus, Department}
import org.openurp.base.std.model.GraduateSeason

import scala.collection.mutable

/** 论文开题题目
 */
class Subject extends LongId, Named {
  var season: GraduateSeason = _
  /** 研究领域 */
  var researchField: Option[String] = None
  /** 面向专业 */
  var majors: mutable.Set[Major] = new mutable.HashSet[Major]
  /** 现有条件 */
  var conditions: Option[String] = None
  /** 对学生要求 */
  var requirements: Option[String] = None
  /** 内容 */
  var contents: Option[String] = None
  /** 状态 */
  var status: AuditStatus = AuditStatus.Draft
  /** 审查意见 */
  var auditOpinion: Option[String] = None
  /** 指导教师 */
  var advisor: Advisor = _
  /** 院系 */
  var depart: Department = _

  def majorNames: String = {
    majors.map(_.name).mkString(",")
  }
}
