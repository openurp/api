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

package org.openurp.std.alter.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.json.{Json, JsonObject}
import org.beangle.commons.lang.{Enums, Strings}
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.std.model.Student
import org.openurp.code.std.model.StdAlterType

import java.time.{Instant, LocalDate}
import scala.collection.mutable

/** 学生异动申请
 */
class StdAlterApply extends LongId, Remark {
  /** 学生 */
  var std: Student = _
  /** 变动类型 */
  var alterType: StdAlterType = _
  /** 申请理由 */
  var reason: String = _
  /** 申请时间 */
  var applyAt: Instant = _
  /** 状态 */
  var status: String = _
  /** 变动开始日期 */
  var alterFrom: Option[LocalDate] = None
  /** 变动结束日期 */
  var alterTo: Option[LocalDate] = None
  /** 提交表单其他 数据 */
  var formDataJson: String = "{}"
  /** 提交需要变更的数据 */
  var alterDataJson: String = "[]"
  /** 审核日志 */
  var steps: mutable.Buffer[StdAlterApplyStep] = Collections.newBuffer[StdAlterApplyStep]
  /** 是否通过 */
  var passed: Option[Boolean] = None
  /** 流程Id */
  var processId: Option[String] = None
  /** 负责人/受理人账户 */
  var assignees: Option[String] = None

  def newStep(name: String, assignees: Option[String]): StdAlterApplyStep = {
    steps find (_.name == name) match
      case None =>
        val step = new StdAlterApplyStep(this, this.steps.size, name)
        this.steps.addOne(step)
        this.status = name
        this.assignees = assignees
        step
      case Some(x) => x
  }

  def alterDatas: Iterable[StdAlterApplyData] = {
    if Strings.isEmpty(alterDataJson) then List.empty
    else
      Json.parseArray(alterDataJson) map {
        case jo: JsonObject =>
          val mo = jo.getObject("meta")
          val meta = Enums.of(classOf[AlterMeta], jo.getInt("id")).get
          new StdAlterApplyData(meta, jo.getString("oldvalue"), jo.getString("oldtext"), jo.getString("newvalue"), jo.getString("newtext"))
      }
  }

  def lastStep: Option[StdAlterApplyStep] = {
    val orderAudits = steps.sortBy(_.idx)
    orderAudits.find(x => x.passed.contains(true)) match
      case Some(x) => Some(orderAudits.find(_.idx > x.idx).getOrElse(orderAudits.last))
      case None => orderAudits.headOption
  }
}

/** 学籍变更申请数据
 */
class StdAlterApplyData {
  /** 异动属性 */
  var meta: AlterMeta = _
  /** 变更前 */
  var oldvalue: Option[String] = None
  /** 变更前值 */
  var oldtext: Option[String] = None
  /** 变更后 */
  var newvalue: Option[String] = None
  /** 变更后值 */
  var newtext: Option[String] = None

  def this(meta: AlterMeta, oldvalue: String, oldtext: String, newvalue: String, newtext: String) = {
    this()
    this.meta = meta
    this.oldvalue = Option(oldvalue)
    this.oldtext = Option(oldtext)
    this.newvalue = Option(newvalue)
    this.newtext = Option(newtext)
  }

  def toJson: JsonObject = {
    new JsonObject(List("meta" -> new JsonObject(List("id" -> meta.id, "name" -> meta.name)), "oldvalue" -> oldvalue, "oldtext" -> oldtext,
      "newvalue" -> newvalue, "newtext" -> newtext))
  }

}
