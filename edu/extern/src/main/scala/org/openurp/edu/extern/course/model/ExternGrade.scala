package org.openurp.edu.extern.course.model

import org.openurp.edu.base.model.Student
import org.beangle.commons.model.LongId
import org.openurp.base.model.Semester

/**
 * 校外成绩
 */
class ExternGrade extends LongId {

  var course: ExternCourse = _

  var semester: Semester = _

  var std: Student = _

  var score: Option[Float] = _

  var scoreText: String = _

  var passed: Boolean = _

  var converted: ConvertedGrade = _
}