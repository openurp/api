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
import org.openurp.base.edu.model.{Direction, Major}
import org.openurp.base.model.{AuditStatus, Department}
import org.openurp.base.std.model.{Grade, Squad, Student}
import org.openurp.std.transfer.config.TransferOption

/** 转专业申请 */
class TransferApply extends LongId, Updated {

  /** 学生 */
  var std: Student = _

  /** 转出年级 */
  var fromGrade: Grade = _

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
  var toGrade: Grade = _

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
  var status: AuditStatus = AuditStatus.Draft

  /** 是否通过 */
  var passed: Option[Boolean] = None

  /** 平均绩点 */
  var gpa: Float = _

  /** 转专业绩点 */
  var transferGpa: Float = _

  /** 专业课GPA */
  var majorGpa: Float = _

  /** 专业课外GPA */
  var otherGpa: Float = _

  /** 包含不及格课程 */
  var hasFail: Boolean = _

  /** 考核分数 */
  var score: Option[Float] = None

  /** 面试分数 */
  var auditionScore: Option[Float] = None

  /** 笔试分数 */
  var writtenScore: Option[Float] = None

  /** 院系面试意见 */
  var departOpinion: Option[String] = None
}
