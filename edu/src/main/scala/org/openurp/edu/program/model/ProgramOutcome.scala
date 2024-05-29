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

import org.beangle.data.model.LongId

/** 培养方案毕业要求
 */
class ProgramOutcome extends LongId {

  var doc: ProgramDoc = _
  /** 顺序号 */
  var idx: Int = _
  /** 毕业要求 */
  var title: String = _
  /** 内容 */
  var contents: String = _

  def this(doc: ProgramDoc, idx: Int, title: String, contents: String) = {
    this()
    this.doc = doc
    this.idx = idx
    this.title = title
    this.contents = contents
  }

  def code: String = s"R${idx}"
}
