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
import org.openurp.base.model.{AuditStatus, Department}

import java.time.LocalDate
import scala.collection.mutable

/** 毕业设计工作计划
 *
 * @author duant
 */
class Plan extends LongId with Cloneable {
  var department: Department = _
  var status: AuditStatus = _
  var times: mutable.Buffer[StageTime] = new mutable.ArrayBuffer[StageTime]
  /** 工作计划审查意见 */
  var auditOpinion: Option[String] = None

  def getStageTime(stage: Stage): StageTime = {
    times.find(x => x.stage == stage).get
  }
//
//  /** 论文题目提交 */
//  var tmtjBegin: LocalDate = _
//  var tmtjEnd: LocalDate = _
//
//  /** 教学院长审查题目 */
//  var tmscBegin: LocalDate = _
//  var tmscEnd: LocalDate = _
//
//  /** 学生初选 */
//  var tmcxBegin: LocalDate = _
//  var tmcxEnd: LocalDate = _
//
//  /** 学生补选 */
//  var tmbxBegin: LocalDate = _
//  var tmbxEnd: LocalDate = _
//
//  /** 确认任务书 */
//  var jsrwsBegin: LocalDate = _
//  var jsrwsEnd: LocalDate = _
//
//  /** 撰写开题报告 */
//  var ktbgBegin: LocalDate = _
//  var ktbgEnd: LocalDate = _
//
//  /** 教师指导Ⅰ */
//  var jszd1Begin: LocalDate = _
//  var jszd1End: LocalDate = _
//
//  /** 中期检查 */
//  var zqjcBegin: LocalDate = _
//  var zqjcEnd: LocalDate = _
//
//  /** 教师指导Ⅱ */
//  var jszd2Begin: LocalDate = _
//  var jszd2End: LocalDate = _
//
//  /** 论文提交截止 */
//  var lwtjEnd: LocalDate = _
//
//  /** 论文评阅截止 */
//  var lwpyEnd: LocalDate = _
//
//  /** 论文答辩截止 */
//  var dbEnd: LocalDate = _

  override def clone(): AnyRef = super.clone()
}
