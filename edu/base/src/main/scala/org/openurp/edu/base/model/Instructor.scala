package org.openurp.edu.base.model

import org.beangle.data.model.{ Coded, LongId, TemporalOn, Updated }
import org.openurp.base.model.Department
import org.openurp.base.model.User
import org.openurp.people.base.model.Person

/**
 * 辅导员
 */
class Instructor extends LongId with Updated with TemporalOn {

  var project: Project = _

  /**人员信息*/
  var user: User = _

}
