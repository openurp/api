package org.openurp.edu.extern.course.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{ Named, Remark, TemporalOn }
import org.openurp.base.model.School

/**
 * 校外课程
 */
class ExternCourse extends LongId with Named with TemporalOn with Remark {

  var school: School = _
  var credits: Float = _
  var enName: Option[String] = _

}