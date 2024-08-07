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

package org.openurp.edu.course.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.edu.clazz.model.Subclazz

/**
 * 具体授课内容
 */
class Lesson extends LongId, Remark {

  def this(plan: ClazzPlan, idx: Int) = {
    this()
    this.plan = plan
    this.idx = idx
  }

  /** 授课计划 */
  var plan: ClazzPlan = _

  /** 针对授课小班 */
  var subclazz: Option[Subclazz] = None

  /** 序号(从1开始) */
  var idx: Int = _

  /** 学时 */
  var creditHours: Int = _

  /** 内容 */
  var contents: String = _

  /** 上课形式 */
  var forms: Option[String] = None

  /** 家庭作业 */
  var homework: Option[String] = None

  /** 自主学习内容 */
  var learning: Option[String] = None

  /** 自主学习课时 */
  var learningHours: Float = _

  /** 是否是考核 */
  var exam: Boolean = _
}
