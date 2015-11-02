/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.model

import java.sql.Date

import org.beangle.data.model.{ LongId, Named, TemporalOn }
import org.beangle.data.model.annotation.code
import org.openurp.code.sin.model.Press
import org.openurp.edu.base.code.model.{ BookAwardType, BookType }

class Textbook extends LongId with TemporalOn with Named {

  var isbn: String = _

  var author: String = _

  var press: Press = _

  var version: String = _

  var price: java.lang.Integer = 0

  var description: String = _

  var remark: String = _

  var bookType: BookType = _

  var publishedOn: Date = _

  var awardType: BookAwardType = _

  var published: Boolean = true

}