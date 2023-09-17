package org.openurp.edu.attendance.model

import org.beangle.data.model.LongId
import org.openurp.base.edu.model.Course
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student

/** 出勤情况
 */
class Attendance extends LongId {

  var std: Student = _

  var semester: Semester = _

  var course: Course = _

  var status: Long = _

  var leave: Long = _
}
