package org.openurp.edu.base.model

import org.beangle.data.model.{ Coded, LongId, TemporalOn, Updated }
import org.beangle.data.model.annotation.code
import org.openurp.base.model.Department
import org.openurp.base.model.User
import org.openurp.edu.base.code.model.TeacherType
import org.openurp.people.base.model.Person

/**
 * 教师信息
 */
class Teacher extends LongId with Updated with TemporalOn {

  /**用户*/
  var user: User = _

  /**所在项目*/
  var project: Project = _

  /**教师类型*/
  var teacherType: TeacherType = _
}

