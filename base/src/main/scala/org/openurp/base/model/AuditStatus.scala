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

package org.openurp.base.model

/**
 * 通用的审核状态枚举类
 * <p>2->50,3->51,4->100,5->99</p>
 */
enum AuditStatus(val id: Int, val name: String) {

  case Draft extends AuditStatus(0, "草稿")
  case Submited extends AuditStatus(1, "已提交")
  case Cancelled extends AuditStatus(2, "已撤销")
  case Blank extends AuditStatus(3, "未提交")

  case PassedByMentor extends AuditStatus(10, "辅导员审核通过")
  case RejectedByMentor extends AuditStatus(11, "辅导员审核不通过")

  case PassedByMaster extends AuditStatus(20, "班主任审核通过")
  case RejectedByMaster extends AuditStatus(21, "班主任审核不通过")

  case PassedByAdvisor extends AuditStatus(30, "导师审核通过")
  case RejectedByAdvisor extends AuditStatus(31, "导师审核不通过")

  case PassedByDepart extends AuditStatus(50, "院系部通过")
  case RejectedByDepart extends AuditStatus(51, "院系部不通过")

  case Rejected extends AuditStatus(99, "审核不通过")
  case Passed extends AuditStatus(100, "审核通过")

  case Published extends AuditStatus(200, "已发布")

  override def toString: String = name
}
