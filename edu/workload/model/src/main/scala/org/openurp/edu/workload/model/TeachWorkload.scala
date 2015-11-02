package org.openurp.edu.workload.model

import org.beangle.data.model.LongId
import org.openurp.base.model.Semester
import org.openurp.edu.teach.code.model.TeachLangType
import org.openurp.edu.base.code.model.CourseCategory
import org.openurp.edu.base.model.Course
import org.openurp.edu.teach.lesson.model.Lesson

class TeachWorkload extends LongId {

  var lesson: Lesson = _

  var course: Course = _

  var semester: Semester = _

  var langType: TeachLangType = _

  var courseCategory: CourseCategory = _

  var modulus: Modulus = _
    
  var stdCount: Integer = _

  var totalWorkload: Integer = _


}