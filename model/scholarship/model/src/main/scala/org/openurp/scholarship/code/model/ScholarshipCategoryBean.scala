package org.openurp.scholarship.code.model

import org.beangle.data.model.bean.{CodedBean, IntIdBean, NamedBean, TemporalOnBean}
import org.openurp.scholarship.code.{ScholarshipCategory, ScholarshipType}

class ScholarshipCategoryBean extends IntIdBean  with CodedBean with NamedBean with TemporalOnBean with ScholarshipCategory {
  
  /**奖学金类型或级别*/
  var scholarshipType: ScholarshipType = _
  
  /**奖学金描述*/
  var discription: String = _
  
  /**评定周期*/
  var period: String = _
  
  /**颁奖单位*/
  var awardUnit: String = _
  
  /**使用状态*/
  var enabled: Boolean = _
  
  /**是否分等级*/
  var beRated: Boolean = _
  
}