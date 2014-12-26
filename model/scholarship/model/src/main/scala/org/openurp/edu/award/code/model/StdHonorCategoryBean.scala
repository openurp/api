package org.openurp.edu.award.code.model

import org.beangle.data.model.bean.{CodedBean, IntIdBean, NamedBean, TemporalOnBean}
import org.openurp.edu.award.code.StipendCategory
import org.openurp.edu.award.code.SubsidyCategory
import org.openurp.edu.award.code.StdHonorCategory

class StdHonorCategoryBean extends IntIdBean  with CodedBean with NamedBean with TemporalOnBean with StdHonorCategory {
  
//  /**个人荣誉类型*/
//  var stipendType: stipendType = _
  
  /**个人荣誉描述*/
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