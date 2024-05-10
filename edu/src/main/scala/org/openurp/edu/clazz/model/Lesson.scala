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

package org.openurp.edu.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.code.edu.model.TeachingMethod

import scala.collection.mutable

/**
 * 授课内容
 */
class Lesson extends LongId, Remark {

  /** 授课计划 */
  var plan: TeachingPlan = _

  /** 针对授课小班 */
  var subclazz: Option[Subclazz] = None

  /** 序号 */
  var idx: Int = _

  /** 内容 */
  var contents: String = _

  /** 授课形式 */
  var methods: mutable.Set[TeachingMethod] = Collections.newSet

  /** 家庭作业 */
  var homework: Option[String] = None

  /** 自主学习内容 */
  var learning: Option[String] = None

  /** 自主学习课时 */
  var learningHours: Int = _
}
