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
import org.openurp.base.model.AuditStatus

import java.time.Instant
import scala.collection.mutable

/** 中期检查
 *
 */
class MidtermCheck extends LongId {

  /** 作者 */
  var writer: Writer = _

  /** 论文写作进度 */
  var proceeding: String = _

  /** 检查细节 */
  var details: mutable.Buffer[MidtermCheckDetail] = new mutable.ArrayBuffer[MidtermCheckDetail]

  /** 审核状态 */
  var status: Option[AuditStatus] = None

  /** 提交时间 */
  var submitAt: Instant = _

  def getDetail(item: MidtermCheckItem): Option[MidtermCheckDetail] = {
    details.find(x => x.item == item)
  }

  def advisorAuditStatus: AuditStatus = {
    if details.isEmpty then AuditStatus.Blank
    else {
      if details.forall(x => x.status == AuditStatus.Passed) then AuditStatus.Passed else AuditStatus.Rejected
    }
  }
}
