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
  override def clone(): AnyRef = super.clone()
}
