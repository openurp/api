package org.openurp.edu.award.code.model

import org.beangle.data.model.bean.{CodedBean, IntIdBean, NamedBean, TemporalOnBean}
import org.openurp.edu.award.code.StipendCategory
import org.openurp.edu.award.code.SubsidyCategory

class SubsidyCategoryBean extends IntIdBean  with CodedBean with NamedBean with TemporalOnBean with SubsidyCategory {
  
//  /**困难补助类型*/
//  var stipendType: stipendType = _
  
  /**困难补助描述*/
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