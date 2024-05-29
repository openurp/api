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

import org.beangle.commons.lang.Strings
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, Named}

/** 培养目标
 */
class ProgramObjective extends LongId, Coded {

  var doc: ProgramDoc = _

  var contents: String = _

  /** 对应毕业要求 */
  var outcomes: String = _

  def this(doc: ProgramDoc, code: String, contents: String) = {
    this()
    this.doc = doc
    this.code = code
    this.contents = contents
  }

  def supportWith(outcome: ProgramOutcome): Boolean = {
    Strings.split(outcomes).toSet.contains(outcome.code)
  }
}
