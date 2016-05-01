package org.openurp.edu.base.model

import org.beangle.data.model.{ Coded, LongId, TemporalOn, Updated }
import org.beangle.data.model.annotation.code
import org.openurp.base.model.Department
import org.openurp.edu.base.code.model.TeacherType
import org.openurp.people.base.model.Person
import org.openurp.base.model.User

/**
 * 教师信息
 */
class Teacher extends LongId with Updated with TemporalOn {

  /**用户*/
  var user: User = _

  /**人员信息 */
  var person: Person = _

  /**所在项目*/
  var project: Project = _

  /**
   * 任教部门
   * 不同于教师所在部门
   */
  var department: Department = _

  /**教师类型*/
  var teacherType: TeacherType = _
}

