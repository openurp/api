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
package org.openurp.edu.curricula.model

import java.util.Locale

import org.beangle.commons.collection.Collections
import org.beangle.data.model.{ Component, IntId, LongId }
import org.beangle.data.model.pojo.{ Named, TemporalOn, Updated }
import org.openurp.edu.base.model.{ Course, Teacher }

/**
 * 课程教学大纲
 * TemporalOn表示大纲会有一定的时效性
 */
class Syllabus extends LongId with Updated with TemporalOn {

  var course: Course = _

  var locale: Locale = _

  var author: Teacher = _

  var attachment: Attachment = new Attachment

  var contents = Collections.newBuffer[SyllabusSection]

  var passed: Boolean = _
}

/**
 * 大纲内容章节
 */
class SyllabusSection extends LongId {
  var title: SyllabusSectionTitle = _
  var syllabus: Syllabus = _
  var content: String = _
}

/**
 * 大纲内容章节标题
 */
class SyllabusSectionTitle extends IntId with Named

/**
 * 大纲附件
 */
class Attachment extends Component with Named {
  var size: Int = _
  var mimeType: String = _
  var key: String = _
}
