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

package org.openurp.edu.course.model

import org.beangle.data.model.LongId
import org.openurp.code.edu.model.SyllabusTopicLabel

/** 教学主题的内容要素
 */
class SyllabusTopicElement extends LongId {

  var topic: SyllabusTopic = _

  var label: SyllabusTopicLabel = _

  var contents: String = _

  def this(topic: SyllabusTopic, label: SyllabusTopicLabel, contents: String) = {
    this()
    this.topic = topic
    this.label = label
    this.contents = contents
  }
}
