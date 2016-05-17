package org.openurp.edu.course.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.TemporalOn
import org.beangle.data.model.Updated
import org.openurp.edu.base.model.Course
import java.util.Locale
import org.openurp.edu.base.model.Teacher
import org.beangle.data.model.IntId
import org.beangle.data.model.Named
import org.beangle.data.model.Component

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
