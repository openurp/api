package org.openurp.edu.evaluation.lesson.model

import org.openurp.edu.base.model.Student
import org.beangle.data.model.LongId
import org.openurp.edu.lesson.model.Lesson
import org.beangle.commons.collection.Collections
import org.openurp.edu.base.model.Teacher

class TextEvaluation extends LongId {
  /** 教学任务 */
  var lesson: Lesson = _
  /** 教师 */
  var teacher: Teacher = _
  /** 学生 */
  var student: Student = _
  var content: String = _
  /** 评教时间 */
  var evaluateAt: java.util.Date = _
  /** 是否确认 */

  var state: Boolean = false

  /** 是否教师评教 */
  var evaluateByTeacher: Boolean = _
  /** 是否确认 */
  //  var isAffirm:Boolean=_

  var teacherRemessages = Collections.newBuffer[TeacherRemessage]

}
