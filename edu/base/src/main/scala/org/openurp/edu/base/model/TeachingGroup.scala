package org.openurp.edu.base.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, Named, TemporalOn, Updated}
import org.openurp.base.model.{Department, User}

import scala.collection.mutable

/** 教研室 */
class TeachingGroup extends LongId with Coded with Named with Updated with TemporalOn {

  var department: Department = _

  /** 负责人 */
  var director: Option[User] = None

  /** 成员 */
  var members: mutable.Buffer[User] = Collections.newBuffer[User]
}
