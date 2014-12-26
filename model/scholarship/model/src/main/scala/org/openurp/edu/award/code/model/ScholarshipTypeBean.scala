package org.openurp.edu.award.code.model

import org.beangle.data.model.bean.NamedBean
import org.beangle.data.model.bean.CodedBean
import org.beangle.data.model.bean.IntIdBean
import org.openurp.edu.award.code.ScholarshipType

class ScholarshipTypeBean extends IntIdBean with CodedBean with NamedBean with ScholarshipType {
  /**使用状态*/
  var enabled: Boolean = _
  /**排序序号*/
  var sequence: String = _
}