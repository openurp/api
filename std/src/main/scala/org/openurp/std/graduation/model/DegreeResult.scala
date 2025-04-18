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

package org.openurp.std.graduation.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.Degree

import java.time.{Instant, LocalDate}

class DegreeResult extends LongId, Updated, Remark {

  /** 所属的毕业审核批次 */
  var batch: GraduateBatch = _

  /** 学生 */
  var std: Student = _

  /** GPA */
  var gpa: Option[Float] = None

  /** 平均分 */
  var ga: Option[Float] = None

  /** 是否通过学位审核 */
  var passed: Option[Boolean] = None

  /** 锁定审核结果 */
  var locked: Boolean = _

  /** 是否已发布 */
  var published: Boolean = _

  /** 学位 */
  var degree: Option[Degree] = None

  /** 外语通过年月 */
  var foreignLangPassedOn: Option[LocalDate] = None

  /** 通过的项目 */
  var passedItems: Option[String] = None

  /** 不通过的的项目 */
  var failedItems: Option[String] = None

  def this(std: Student, batch: GraduateBatch) = {
    this()
    this.batch = batch
    this.std = std
    this.updatedAt = Instant.now
  }

  def disciplineCode: String = {
    std.state.map(_.major.getDisciplineCode(std.beginOn)).getOrElse("")
  }

  def addPassed(item: String, remark: String): Unit = {
    passedItems match
      case None => passedItems = Some(s"${item}:${remark}")
      case Some(s) => passedItems = Some(s + "\n" + s"${item}:${remark}")
  }

  def addFailed(item: String, remark: String): Unit = {
    failedItems match
      case None => failedItems = Some(s"${item}:${remark}")
      case Some(s) => failedItems = Some(s + "\n" + s"${item}:${remark}")
  }
}
