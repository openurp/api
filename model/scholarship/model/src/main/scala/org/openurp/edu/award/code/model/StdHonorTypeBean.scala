package org.openurp.edu.award.code.model

import org.beangle.data.model.bean.NamedBean
import org.beangle.data.model.bean.CodedBean
import org.beangle.data.model.bean.IntIdBean
import org.openurp.edu.award.code.ScholarshipType
import org.openurp.edu.award.code.StdHonorType

class StdHonorTypeBean extends IntIdBean with CodedBean with NamedBean with StdHonorType {
  /**使用状态*/
  var enabled: Boolean = _
  /**排序序号*/
  var sequence: String = _
}