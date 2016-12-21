package org.openurp.edu.exam.model

import scala.collection.mutable.Buffer

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.time.HourMinute
import org.beangle.data.model.LongId
import org.openurp.base.model.Semester
import org.openurp.edu.base.code.model.ExamType
import org.openurp.edu.base.model.Project

class ExamTask extends LongId {
  var project: Project = _

  var semester: Semester = _

  var examType: ExamType = _

  var examPaper: ExamPaper = _

  var examOn: Option[java.sql.Date] = None

  var beginAt: HourMinute = _

  var endAt: HourMinute = _

  var stdCount: Int = _

  var examLessons: Buffer[ExamLesson] = Collections.newBuffer[ExamLesson]
}