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
package org.openurp.edu.base.model

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.code
import org.beangle.data.model.pojo.Named
import org.beangle.data.model.pojo.Remark
import org.beangle.data.model.pojo.TemporalOn
import org.openurp.code.sin.model.Press
import org.openurp.code.sin.model.BookCategory
import org.openurp.edu.base.code.model.BookAwardType
import org.openurp.edu.base.code.model.BookType
import java.time.LocalDate

/**
 * 教材基本信息
 */
class Textbook extends LongId with TemporalOn with Named with Remark {

  var project: Project = _

  var isbn: Option[String] = None

  var author: String = _

  var translator: Option[String] = None

  var press: Option[Press] = None

  var version: String = _

  var price: Option[Float] = None

  var description: Option[String] = None

  var bookType: Option[BookType] = None

  var category: Option[BookCategory] = None

  var publishedOn: LocalDate = _

  var awardType: Option[BookAwardType] = None

  var awardOrg: Option[String] = None

  var series: Option[String] = None

  var madeInSchool: Boolean = false

}
