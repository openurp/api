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
package org.openurp.edu.textbook.lesson.model

import org.beangle.data.model.bind.Mapping
import org.openurp.edu.textbook.lesson.MaterialStatus

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("date")

    bind[LessonMaterial] on (e => declare(
      e.lesson is notnull,
      e.referenceBooks is length(500),
      e.extra is length(200),
      e.useExplain is length(300),
      e.remark is length(200)))
  }

}
