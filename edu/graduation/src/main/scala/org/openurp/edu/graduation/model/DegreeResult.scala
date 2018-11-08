/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright (c) 2005, The OpenURP Software.
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
package org.openurp.edu.graduation.model

import scala.collection.mutable.Buffer

import org.beangle.commons.collection.Collections
import org.beangle.data.model.Component
import org.openurp.code.edu.model.Degree

class DegreeResult extends Component {

  /** 学位审核详细结果 */
  var items: Buffer[DegreeAuditItem] = Collections.newBuffer[DegreeAuditItem]

  /**
   * 是否通过学位审核
   */
  var passed: Boolean = _

  /** 锁定毕业审核结果 */
  var locked: Boolean = _

  /** 是否已发布 */
  var published: Boolean = _

  /** 毕业备注 */
  var comments: Option[String] = None

  /** 学位 */
  var degree: Option[Degree] = None
}
