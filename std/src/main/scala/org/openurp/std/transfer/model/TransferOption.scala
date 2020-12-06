/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.std.transfer.model

import java.time.LocalDate

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.model.{Department, User}
import org.openurp.base.edu.model.{Direction, Major}

/** 转专业招收专业
 * */
class TransferOption extends LongId with Remark {

  /** 转专业招生方案 */
  var scheme: TransferScheme = _

  /** 院系 */
  var depart: Department = _

  /** 专业 */
  var major: Major = _

  /** 方向 */
  var direction: Option[Direction] = None

  /** 计划人数 */
  var planCount: Int = _

  /** 报名人数 */
  var currentCount: Int = _

  /** 负责联络的老师 */
  var manager: Option[User] = None

  /** 联络方式 */
  var contactInfo: Option[String] = None

  /** 咨询日期 */
  var consultOn: Option[LocalDate] = None

  /** 咨询地址 */
  var consultAddr: Option[String] = None

  /** 考核日期 */
  var examOn: Option[LocalDate] = None

  /** 考核地址 */
  var examAddr: Option[String] = None

  /** 面试内容 */
  var auditionContent: Option[String] = None

  /** 笔试内容 */
  var writtenContent: Option[String] = None

  /** 面试分数占总分比例 */
  var auditionPercent: Int = _

  /** 笔试分数占总分比例 */
  var writtenPercent: Int = _
}


