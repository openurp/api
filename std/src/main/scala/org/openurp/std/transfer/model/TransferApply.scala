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

package org.openurp.std.transfer.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.Department
import org.openurp.base.edu.AuditStatus
import org.openurp.base.edu.model.{Direction, Major, Squad, Student}

/** 转专业申请 */
class TransferApply extends LongId with Updated {

  /** 学生 */
  var std: Student = _

  /** 转出年级 */
  var fromGrade: String = _

  /** 转出院系 */
  var fromDepart: Department = _

  /** 转出专业 */
  var fromMajor: Major = _

  /** 转出专业方向 */
  var fromDirection: Option[Direction] = None

  /** 转出班级 */
  var fromSquad: Option[Squad] = None

  /** 选择的招生专业 */
  var option: TransferOption = _

  /** 转入年级 */
  var toGrade: Option[String] = None

  /** 转入院系 */
  var toDepart: Department = _

  /** 转入专业 */
  var toMajor: Major = _

  /** 转入方向 */
  var toDirection: Option[Direction] = None

  /** 转入班级 */
  var toSquad: Option[Squad] = None

  /** 是否服从调剂 */
  var adjustable: Boolean = _

  /** 申请理由 */
  var reason: String = _

  /** 联系电话 */
  var mobile: String = _

  /** 联系邮箱 */
  var email: String = _

  /** 状态 */
  var auditStatus: AuditStatus = AuditStatus.Draft

  /** 是否通过 */
  var passed: Option[Boolean] = None

  /** 平均绩点 */
  var gpa: Float = _

  /** 专业课GPA */
  var majorGpa: Float = _

  /** 专业课外GPA */
  var otherGpa: Float = _

  /** 考核分数 */
  var score: Option[Float] = None

  /** 面试分数 */
  var auditionScore: Option[Float] = None

  /** 笔试分数 */
  var writtenScore: Option[Float] = None

  /** 院系面试意见 */
  var departOpinion: Option[String] = None
}
