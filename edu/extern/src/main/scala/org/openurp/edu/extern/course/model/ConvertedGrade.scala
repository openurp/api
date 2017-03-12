package org.openurp.edu.extern.course.model

import org.beangle.commons.model.LongId
import org.openurp.edu.base.model.Student
import org.openurp.base.model.Semester
import org.openurp.edu.base.model.Course
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Buffer

/**
 * 转换成我校后的成绩
 */
class ConvertedGrade extends LongId {
  var semester: Semester = _

  var std: Student = _

  var course: Course = _

  var score: Option[Float] = _

  var scoreText: String = _

  var passed: Boolean = _

  var gp: Option[Float] = None

  var sources: Buffer[ExternGrade] = new ListBuffer[ExternGrade]
}