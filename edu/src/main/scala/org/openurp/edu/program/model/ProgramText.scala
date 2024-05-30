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

package org.openurp.edu.program.model

import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Named

/** 大纲内容
 */
@beta
class ProgramText extends LongId, Named {

  var doc: ProgramDoc = _

  /** 标题 */
  var title: String = _

  /** 内容 */
  var contents: String = _

  /** 链接表格的名称 */
  var linkTable: Option[String] = None

  def this(doc: ProgramDoc, name: String, title: String, contents: String) = {
    this()
    this.title = title
    this.doc = doc
    this.name = name
    this.contents = contents
  }
}
