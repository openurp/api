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

package org.openurp.base.edu.model

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.code
import org.beangle.data.model.pojo.{Named, Remark, TemporalOn}
import org.openurp.base.model.Project
import org.openurp.code.edu.model.{BookAwardType, BookType}
import org.openurp.code.sin.model.{BookCategory, Press}

import java.time.LocalDate

/**
 * 教材基本信息
 */
class Textbook extends LongId with TemporalOn with Named with Remark {

  var project: Project = _

  var isbn: Option[String] = None

  var author: String = _

  /** 译作者 */
  var translator: Option[String] = None

  /**出版社*/
  var press: Option[Press] = None

  var edition: String = _

  var price: Option[Float] = None

  var description: Option[String] = None

  var bookType: Option[BookType] = None

  var category: Option[BookCategory] = None

  /** 出版日期 */
  var publishedOn: LocalDate = _

  var awardType: Option[BookAwardType] = None

  var awardOrg: Option[String] = None

  var series: Option[String] = None

  var madeInSchool: Boolean = false

}
