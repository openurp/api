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
package org.openurp.edu.course.model

import scala.collection.mutable.Buffer

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.beangle.data.model.pojo.Updated
import org.openurp.edu.base.model.Textbook

/**
 * 教学材料说明
 *  包括教材、参考书等
 */
class Material extends LongId with Serializable with Cloneable with Updated with Remark {

  /**教材选用类型*/
  var state: BookAdoption.State = _

  /**教材列表*/
  var books: Buffer[Textbook] = Collections.newBuffer[Textbook]

  /**参考书*/
  var references: Option[String] = None

  /**其它资料*/
  var extra: Option[String] = None

  /**选用理由*/
  var reason: Option[String] = None

  /**教学任务*/
  var clazz: Clazz = _

  /**已提交*/
  var confirmed: Boolean = _

  /**历史已经订购过*/
  var ordered: Boolean = _

  /**审核通过*/
  var passed: Option[Boolean] = None
}
