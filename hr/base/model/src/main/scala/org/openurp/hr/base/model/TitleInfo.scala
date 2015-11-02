package org.openurp.hr.base.model

import org.beangle.data.model.{ LongId, TemporalOn }
import org.openurp.code.job.model.ProfessionalTitle

/**
 * 职称信息
 */
class TitleInfo extends LongId with TemporalOn {

  var staff: Staff = _

  /** 职称 */
  var title: ProfessionalTitle = _
}