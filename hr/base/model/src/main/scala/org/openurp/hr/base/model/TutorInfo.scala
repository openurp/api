package org.openurp.hr.base.model

import org.beangle.data.model.TemporalOn
import org.beangle.data.model.LongId
import org.openurp.code.hr.model.TutorType

/**
 * 导师信息
 */
class TutorInfo extends LongId with TemporalOn {

  var staff: Staff = _

  /**导师类型*/
  var tutorType: TutorType = _

}