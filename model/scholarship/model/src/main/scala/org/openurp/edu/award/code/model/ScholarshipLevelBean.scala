package org.openurp.edu.award.code.model

import org.beangle.data.model.bean.NamedBean
import org.beangle.data.model.bean.CodedBean
import org.beangle.data.model.bean.IntIdBean
import org.openurp.edu.award.code.ScholarshipLevel
import org.openurp.edu.award.code.ScholarshipCategory

class ScholarshipLevelBean extends IntIdBean with CodedBean with NamedBean with ScholarshipLevel {
  
  /**奖学金种类*/
  var scholarshipCategory: ScholarshipCategory=_
  
  /**奖励金额*/
  var awardAmount: Float = _
  
   /**使用状态*/
  var enabled: Boolean = _
  
  /**描述*/
   
  var discription: String = _
}