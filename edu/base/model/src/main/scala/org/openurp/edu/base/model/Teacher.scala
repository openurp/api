package org.openurp.edu.base.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.{ Coded, LongId, TemporalOn, Updated }
import org.beangle.data.model.Named
import org.openurp.base.model.Department
import org.openurp.code.edu.model.Degree
import org.openurp.code.hr.model.TutorType
import org.openurp.code.job.model.ProfessionalTitle
import org.openurp.edu.base.code.model.TeacherType
import org.openurp.people.base.model.Person

/**
 * 教师信息
 */
class Teacher extends LongId with Updated with Coded with TemporalOn {

  /**所在项目*/
  var project: Project = _

  /**人员信息*/
  var person: Person = _

  /**状态*/
  var state: TeacherState = _

  /**历史状态*/
  var states = Collections.newBuffer[TeacherState]

  /**教师类型*/
  var teacherType: TeacherType = _
}

class TeacherState extends LongId with TemporalOn {

  var teacher: Teacher = _

  /** 部门 */
  var department: Department = _

  /** 职称 */
  var title: ProfessionalTitle = _

  /**学位*/
  var degree: Degree = _

  /**导师类型*/
  var tutorType: TutorType = _
}
