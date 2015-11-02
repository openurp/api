package org.openurp.edu.workload.model

import org.beangle.data.model.LongId
import org.openurp.edu.teach.code.model.TeachLangType
import org.openurp.edu.base.code.model.CourseCategory

class Modulus extends LongId {

  var langType: TeachLangType = _

  var courseCategory: CourseCategory = _

  var moduluMark: String = _

  var maxStdCount: Integer = _

  var minStdCount: Integer = _

  var modulusValue: Float = _


}