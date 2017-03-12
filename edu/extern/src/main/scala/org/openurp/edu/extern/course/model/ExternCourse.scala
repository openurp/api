package org.openurp.edu.extern.course.model

import org.openurp.base.model.School
import org.beangle.commons.model.LongId
import org.beangle.commons.model.Named
import org.beangle.commons.model.TemporalOn
import org.beangle.commons.model.Remark

/**
 * 校外课程
 */
class ExternCourse extends LongId with Named with TemporalOn with Remark {

  var school: School = _
  var credits: Float = _
  var enName: Option[String] = _

}