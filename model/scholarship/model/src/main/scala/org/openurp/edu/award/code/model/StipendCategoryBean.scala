package org.openurp.edu.award.code.model

import org.beangle.data.model.bean.{CodedBean, IntIdBean, NamedBean, TemporalOnBean}
import org.openurp.edu.award.code.StipendCategory

class StipendCategoryBean extends IntIdBean  with CodedBean with NamedBean with TemporalOnBean with StipendCategory {
  
//  /**助学金类型*/
//  var stipendType: stipendType = _
  
  /**助学金描述*/
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