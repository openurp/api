package org.openurp.hr.info.model

import org.beangle.data.model.TemporalOn
import org.beangle.data.model.LongId
import org.openurp.hr.base.model.Staff
import org.openurp.code.hr.model.EmployType
import org.openurp.code.hr.model.StaffSourceType
import java.sql.Date

/**
 * 教职工来源信息
 * 教育部标准JY/T 1001 6.4.6
 */
class SourceInfo extends LongId {

  var staff: Staff = _

  /**教职工来源*/
  var sourceType: StaffSourceType = _

  /**参加工作日期*/
  var workStartOn: Date = _

  /**来校日期*/
  var employOn: Date = _

  /**用人形式*/
  var employType: EmployType = _

}