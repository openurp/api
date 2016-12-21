package org.openurp.edu.exam.model

import scala.collection.mutable.Buffer

import org.beangle.data.model.{ Coded, LongId, Named, Remark }
import org.openurp.code.edu.model.ClassroomType
import org.openurp.edu.base.code.model.ExamType
import org.openurp.edu.base.model.{ Course, Project }

class ExamPaper extends LongId with Coded with Named with Remark {

  var project: Project = _

  var examType: ExamType = _

  var courses: Buffer[Course] = _

  var roomType: Option[ClassroomType] = None

  var duration: Short = _

}