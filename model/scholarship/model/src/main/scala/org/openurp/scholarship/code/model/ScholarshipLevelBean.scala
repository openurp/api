package org.openurp.scholarship.code.model

import org.beangle.data.model.bean.NamedBean
import org.beangle.data.model.bean.CodedBean
import org.beangle.data.model.bean.IntIdBean
import org.openurp.scholarship.code.ScholarshipLevel

class ScholarshipLevelBean extends IntIdBean with CodedBean with NamedBean with ScholarshipLevel {
  
  /**奖励金额*/
  var awardAmount: Float = _
  
   /**使用状态*/
  var enabled: Boolean = _
  /**描述*/
  
  var discription: String = _
}