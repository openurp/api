package org.openurp.edu.exam.model

import org.beangle.data.model.LongId
import org.openurp.edu.lesson.model.Lesson

class ExamLesson extends LongId {

  var task: ExamTask = _

  var lesson: Lesson = _

  var stdCount: Int = _
  
}