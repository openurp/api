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
import org.beangle.data.model.pojo.Named

class ProgramTable extends LongId, Named {
  var doc: ProgramDoc = _
  var contents: String = _
  var caption: String = _

  def this(doc: ProgramDoc, name: String, caption: String, contents: String) = {
    this()
    this.doc = doc
    this.name = name
    this.caption = caption
    this.contents = contents
  }
}
