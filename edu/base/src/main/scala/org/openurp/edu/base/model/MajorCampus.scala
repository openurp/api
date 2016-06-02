package org.openurp.edu.base.model

import org.beangle.data.model.IntId
import org.openurp.base.model.Campus
import org.openurp.edu.base.code.model.Education
/**
 * 专业对应校区
 */
class MajorCampus extends IntId {

  /** 年级,形式为yyyy-p */
  var grade: String = _

  /** 专业 */
  var major: Major = _

  /** 校区信息 */
  var education: Education = _

  /** 对应学期 */
  var terms: Terms = _

  /** 校区信息 */
  var campus: Campus = _

}
