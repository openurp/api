package org.openurp.edu.evaluation.lesson.stat.model

import org.beangle.data.model.LongId
import org.openurp.edu.base.model.Teacher
import org.openurp.base.model.Semester

class FinalTeacherScore extends LongId with Rank {
  var teacher: Teacher = _
  var semester: Semester = _
  var stdScore: Float = _
  var supviScore: Float = _
  var departScore: Float = _
  var score: Float = _

}
