/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2016, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.course.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.model.LongId
import org.beangle.commons.model.TemporalOn
import org.beangle.commons.model.Updated
import org.openurp.edu.base.model.Course
import java.util.Locale
import org.openurp.edu.base.model.Teacher
import org.beangle.commons.model.IntId
import org.beangle.commons.model.Named
import org.beangle.commons.model.Component

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
