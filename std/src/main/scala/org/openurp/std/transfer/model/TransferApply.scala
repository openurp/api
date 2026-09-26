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
import org.beangle.data.model.pojo.Updatable
import org.openurp.base.edu.model.{Major, MajorDirection}
import org.openurp.base.model.{AuditStatus, Department}
import org.openurp.base.std.model.{Grade, Squad, Student}
import org.openurp.std.transfer.config.TransferOption
import scala.compiletime.uninitialized

/** 转专业申请 */
class TransferApply extends LongId, Updatable {

  /** 学生 */
  var std: Student = uninitialized

  /** 转出年级 */
  var fromGrade: Grade = uninitialized

  /** 转出院系 */
  var fromDepart: Department = uninitialized

  /** 转出专业 */
  var fromMajor: Major = uninitialized

  /** 转出专业方向 */
  var fromDirection: Option[MajorDirection] = None

  /** 转出班级 */
  var fromSquad: Option[Squad] = None

  /** 选择的招生专业 */
  var option: TransferOption = uninitialized

  /** 转入年级 */
  var toGrade: Grade = uninitialized

  /** 转入院系 */
  var toDepart: Department = uninitialized

  /** 转入专业 */
  var toMajor: Major = uninitialized

  /** 转入方向 */
  var toDirection: Option[MajorDirection] = None

  /** 转入班级 */
  var toSquad: Option[Squad] = None

  /** 是否服从调剂 */
  var adjustable: Boolean = uninitialized

  /** 申请理由 */
  var reason: String = uninitialized

  /** 联系电话 */
  var mobile: String = uninitialized

  /** 联系邮箱 */
  var email: String = uninitialized

  /** 状态 */
  var status: AuditStatus = AuditStatus.Draft

  /** 是否通过 */
  var passed: Option[Boolean] = None

  /** 平均绩点 */
  var gpa: Float = uninitialized

  /** 转专业绩点 */
  var transferGpa: Float = uninitialized

  /** 专业课GPA */
  var majorGpa: Float = uninitialized

  /** 专业课外GPA */
  var otherGpa: Float = uninitialized

  /** 包含不及格课程 */
  var hasFail: Boolean = uninitialized

  /** 考核分数 */
  var score: Option[Float] = None

  /** 面试分数 */
  var auditionScore: Option[Float] = None

  /** 笔试分数 */
  var writtenScore: Option[Float] = None

  /** 院系面试意见 */
  var departOpinion: Option[String] = None
}
