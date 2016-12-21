package org.openurp.edu.exam.model

import org.openurp.base.model.Semester
import org.beangle.data.model.LongId
import org.openurp.edu.lesson.model.Lesson
import org.openurp.edu.base.code.model.ExamType
import org.openurp.edu.base.model.Student
import org.openurp.edu.base.code.model.ExamStatus
import org.beangle.data.model.Remark

class ExamStudent extends LongId with Remark {

  var lesson: Lesson = _

  var semester: Semester = _

  var std: Student = _

  var examRoom: Option[ExamRoom] = None

  var examType: ExamType = _

  var examStatus: ExamStatus = _

  var seatNo: Short = _

}