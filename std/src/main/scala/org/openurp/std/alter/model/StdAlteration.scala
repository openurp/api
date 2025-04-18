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

package org.openurp.std.alter.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student
import org.openurp.code.std.model.{StdAlterReason, StdAlterType}

import java.time.{Instant, LocalDate}
import scala.collection.mutable
import scala.collection.mutable.Buffer

/**
 * 学籍异动
 */
class StdAlteration extends LongId, Updated, Remark {
  /** 变动学生 */
  var std: Student = _
  /** 学年学期 */
  var semester: Semester = _
  /** 变动类型 */
  var alterType: StdAlterType = _
  /** 变动日期 */
  var alterOn: LocalDate = _
  /** 变动原因 */
  var reason: Option[StdAlterReason] = None
  /** 变动项目 */
  var items: mutable.Buffer[StdAlterationItem] = Collections.newBuffer[StdAlterationItem]
  /** 是否生效 */
  var effective: Boolean = _
  /** 批准文号 */
  var docNum: Option[String] = None

  /** 使用学生、日期和异动类型初始化一个学籍异动
   *
   * @param std
   * @param alterType
   * @param semester
   * @param alterOn
   */
  def this(std: Student, alterType: StdAlterType, semester: Semester, alterOn: LocalDate) = {
    this()
    this.std = std
    this.alterType = alterType
    this.semester = semester
    this.alterOn = alterOn
    this.effective = true
    this.updatedAt = Instant.now
  }

  def addItem(i: StdAlterationItem): Unit = {
    i.alteration = this
    this.items.addOne(i)
  }

  def getItem(meta: AlterMeta): Option[StdAlterationItem] = {
    items.find(_.meta == meta)
  }
}
